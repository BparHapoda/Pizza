package com.example.pizza.servlets;

import com.example.pizza.dtos.OrderForm;
import com.example.pizza.dtos.UserDto;
import com.example.pizza.models.*;
import com.example.pizza.repositories.OrderRepositoryImpl;
import com.example.pizza.repositories.OrdersRepository;

import com.example.pizza.repositories.UserRepositoryImpl;
import com.example.pizza.repositories.UsersRepository;
import com.example.pizza.services.*;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
private ObjectMapper objectMapper;
    private OrderService orderService;
    private SignInService signInService;
    private UserService userService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("db");
        OrdersRepository orderRepository = new OrderRepositoryImpl(dataSource);
        UsersRepository usersRepository=new UserRepositoryImpl(dataSource);
        this.orderService = new OrderServiceImpl(orderRepository);
        this.signInService=new SignInServiceImpl(usersRepository);
        this.userService=new UserServiceImpl(usersRepository);
this.objectMapper=new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        String login= (String) session.getAttribute("login");
        Integer id=signInService.getUserIdByLogin(login);
        UserDto userDto=userService.getUserFromRepository(id);

        Pizza pizza;
        String pizzaName=req.getParameter("pizza");
        switch (pizzaName){
            case "Маргарита" :{pizza= new PizzaMargarita();break;}
            case "Четыре сыра" :{pizza= new PizzaCaprichosa();break;}
            case "Капричоза" :{pizza= new PizzaFourCheese();break;}
            case "Гавайская" :{pizza= new PizzaHawaji();break;}

            default:
                throw new IllegalStateException("Unexpected value: " + pizzaName);
        }


        OrderForm orderForm =OrderForm.builder()
                .clientId(id)
                .pizza(pizza.getName().getTitle())
                .login(login)
                .receipt(objectMapper.writeValueAsString(pizza.getReceipt()))
                .name(userDto.getName())
                .surname(userDto.getSurname()).
                phone(userDto.getPhone()).
                address(req.getParameter("address"))
                .email(userDto.getEmail())
                .build();
        if(req.getParameter("top").equals("on")){
            List <Topping>toppings =new ArrayList<>();
            if(req.getParameter("Сыр")!=null){toppings.add(Topping.CHEESE);}
            if(req.getParameter("Яйцо")!=null){toppings.add(Topping.EGG);}
            if(req.getParameter("Ветчина")!=null){toppings.add(Topping.HAM);}
            if(req.getParameter("Ананас")!=null){toppings.add(Topping.PINE_APPLE);}
            if(req.getParameter("Рыба")!=null){toppings.add(Topping.FISH);}
            if(req.getParameter("Острый перец")!=null){toppings.add(Topping.CHILI);}
            if(req.getParameter("Оливки")!=null){toppings.add(Topping.OLIVES);}
            if(toppings.size()>0){
                orderForm.setToppings(objectMapper.writeValueAsString(toppings));
            }

        }

        orderService.order(orderForm);
        req.getRequestDispatcher("orderDone.jsp").forward(req, resp);
    }
}
