package com.example.pizza.servlets;

import com.example.pizza.dtos.OrderForm;
import com.example.pizza.repositories.OrderRepositoryImpl;
import com.example.pizza.repositories.OrdersRepository;

import com.example.pizza.services.OrderService;
import com.example.pizza.services.OrderServiceImpl;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("db");
        OrdersRepository orderRepository = new OrderRepositoryImpl(dataSource);
        this.orderService = new OrderServiceImpl(orderRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderForm orderForm = OrderForm.builder()
                .login(req.getParameter("login"))
                .address(req.getParameter("address"))
                .pizza(req.getParameter("pizza"))
                .build();
        orderService.order(orderForm);
        req.getRequestDispatcher("orderDone.jsp").forward(req, resp);
    }
}
