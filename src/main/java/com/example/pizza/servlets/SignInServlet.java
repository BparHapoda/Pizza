package com.example.pizza.servlets;

import com.example.pizza.dtos.SignInForm;
import com.example.pizza.models.PizzaNames;
import com.example.pizza.models.Topping;
import com.example.pizza.repositories.UserRepositoryImpl;
import com.example.pizza.repositories.UsersRepository;
import com.example.pizza.services.SignInService;
import com.example.pizza.services.SignInServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context=config.getServletContext();
        DataSource dataSource= (DataSource) context.getAttribute("db");
        UsersRepository usersRepository= new UserRepositoryImpl(dataSource);
        this.signInService=new SignInServiceImpl(usersRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("signIn.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInForm signInForm= new SignInForm(req.getParameter("login"),req.getParameter("password"));
        if(signInService.login(signInForm)){

            req.setAttribute("login",signInForm.getLogin());
List <String> pizzas=new ArrayList<>();
for (PizzaNames pizza : PizzaNames.values()){
    pizzas.add(pizza.getTitle());
}
req.setAttribute("pizzas",pizzas);

            List <String> toppings=new ArrayList<>();
            for (Topping topping : Topping.values()){
                toppings.add(topping.getTitle());
            }
            req.setAttribute("toppings",toppings);



            req.getRequestDispatcher("order.jsp").forward(req,resp);
        }
        else {
            resp.sendRedirect("/");
        }

    }
}
