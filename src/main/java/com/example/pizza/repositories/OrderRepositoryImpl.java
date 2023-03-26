package com.example.pizza.repositories;

import com.example.pizza.models.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class OrderRepositoryImpl implements OrdersRepository {
    private static String INSERT = "INSERT INTO orders (item,login,address,customer_id,receipt,toppings,name," +
            "surname,phone,email) values (?,?,?,?,?,?,?,?,?,?)";
    private DataSource dataSource;

    public OrderRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Order order) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, order.getItem());
            statement.setString(2, order.getLogin());
            statement.setString(3, order.getAddress());
            statement.setInt(4, order.getCustomerId());
            statement.setString(5, order.getReceipt());
            statement.setString(6, order.getToppings());
            statement.setString(7, order.getName());
            statement.setString(8, order.getSurname());
            statement.setString(9, order.getPhone());
            statement.setString(10, order.getEmail());





            int affRows = statement.executeUpdate();
            if (affRows != 1) {
                throw new RuntimeException("Запись в БД не удалась");
            }
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt("id"));
            } else {
                throw new SQLException("Мы не можем получить ID из БД");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}