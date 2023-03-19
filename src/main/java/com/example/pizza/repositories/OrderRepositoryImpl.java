package com.example.pizza.repositories;

import com.example.pizza.models.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class OrderRepositoryImpl implements OrdersRepository {
    private static String INSERT = "INSERT INTO orders (login,item,address) values (?,?,?)";
    private DataSource dataSource;

    public OrderRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Order order) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, order.getLogin());
            statement.setString(2, order.getPizza());
            statement.setString(3, order.getAdress());

            int affRows = statement.executeUpdate();
            if (affRows != 1) {
                throw new RuntimeException("Запись в БД не удалась");
            }
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong("id"));
            } else {
                throw new SQLException("Мы не можем получить ID из БД");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}