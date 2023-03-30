package com.example.pizza.servlets;

import com.example.pizza.dtos.OrderForm;
import com.example.pizza.dtos.UserDto;
import com.example.pizza.models.*;
import com.example.pizza.repositories.OrderRepositoryImpl;
import com.example.pizza.repositories.OrdersRepository;

import com.example.pizza.repositories.UserRepositoryImpl;
import com.example.pizza.repositories.UsersRepository;
import com.example.pizza.services.*;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.codehaus.jackson.map.ObjectMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private ObjectMapper objectMapper;
    private OrderService orderService;
    private SignInService signInService;
    private UserService userService;
    private MessageService messageService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("db");
        OrdersRepository orderRepository = new OrderRepositoryImpl(dataSource);
        UsersRepository usersRepository = new UserRepositoryImpl(dataSource);
        this.orderService = new OrderServiceImpl(orderRepository);
        this.signInService = new SignInServiceImpl(usersRepository);
        this.userService = new UserServiceImpl(usersRepository);
        this.objectMapper = new ObjectMapper();
        this.messageService=new EmailService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        Integer id = signInService.getUserIdByLogin(login);
        UserDto userDto = userService.getUserFromRepository(id);


        Pizza pizza;
        String pizzaName = req.getParameter("pizza");
        switch (pizzaName) {
            case "Маргарита": {
                pizza = new PizzaMargarita();
                break;
            }
            case "Четыре сыра": {
                pizza = new PizzaCaprichosa();
                break;
            }
            case "Капричоза": {
                pizza = new PizzaFourCheese();
                break;
            }
            case "Гавайская": {
                pizza = new PizzaHawaji();
                break;
            }
            case "myPizza": {
                pizza = new PizzaMy();
                for (Ingridient ingridient : Arrays.asList(Ingridient.values())
                ) {

                    if (req.getParameter(ingridient.getTitle()) != null) {
                        pizza.getReceipt().add(ingridient);
                    }
                }


                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + pizzaName);
        }
        Address address = Address.builder().
                city(req.getParameter("city"))
                .street(req.getParameter("street"))
                .house(req.getParameter("house"))
                .korpus(req.getParameter("korpus"))
                .flate(req.getParameter("flate")).
                build();

        OrderForm orderForm = OrderForm.builder()
                .clientId(id)
                .pizza(pizza.getName().getTitle())
                .login(login)
                .name(userDto.getName())
                .surname(userDto.getSurname()).
                phone(userDto.getPhone()).
                address(objectMapper.writeValueAsString(address))
                .email(userDto.getEmail())
                .build();

        List<String> stringReceipt = new ArrayList<>();
        for (Ingridient ingridient : pizza.getReceipt()) {
            stringReceipt.add(ingridient.getTitle());
        }
        orderForm.setReceipt(objectMapper.writeValueAsString(stringReceipt));

        List<String> toppings = new ArrayList<>();
        if (req.getParameter("top") != null) {

            for (Topping topping : Arrays.asList(Topping.values())
            ) {
                if (req.getParameter(topping.getTitle()) != null) {
                    toppings.add(topping.getTitle());
                }
            }

            if (toppings.size() > 0) {
                orderForm.setToppings(objectMapper.writeValueAsString(toppings));
            }

        }


        orderService.order(orderForm);
        req.setAttribute("order",orderForm);
        try {
            messageService.send(orderForm);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("orderDone.jsp").forward(req, resp);
    }
}
