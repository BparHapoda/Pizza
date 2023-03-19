package com.example.pizza.servlets;

import com.example.pizza.dtos.SignInForm;
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
            req.getRequestDispatcher("order.jsp").forward(req,resp);
        }
        else {
            resp.sendRedirect("/");
        }

    }
}
