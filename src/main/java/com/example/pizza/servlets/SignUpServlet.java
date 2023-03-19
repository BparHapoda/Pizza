package com.example.pizza.servlets;

import com.example.pizza.dtos.SignUpForm;
import com.example.pizza.repositories.UserRepositoryImpl;
import com.example.pizza.repositories.UsersRepository;
import com.example.pizza.services.SignUpService;
import com.example.pizza.services.SignUpServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
private SignUpService signUpService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context=config.getServletContext();
        DataSource dataSource= (DataSource) context.getAttribute("db");
        UsersRepository usersRepository= new UserRepositoryImpl(dataSource);
        this.signUpService=new SignUpServiceImpl(usersRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("signUp.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpForm signUpForm =SignUpForm.builder()
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .nickname(req.getParameter("nickname"))
                .build();
        signUpService.signUp(signUpForm);
        resp.sendRedirect("/signIn");
    }
}
