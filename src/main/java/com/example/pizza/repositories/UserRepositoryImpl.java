package com.example.pizza.repositories;

import com.example.pizza.dtos.SignInForm;
import com.example.pizza.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UsersRepository {

    private static String INSERT = "INSERT INTO users (name,surname,login,password,nickname) values (?,?,?,?,?)";
    private static String SELECT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login=? AND password=?";
    private DataSource dataSource;

    public UserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getNickname());
            int affRows = statement.executeUpdate();
            if (affRows != 1) {
                throw new RuntimeException("Запись в БД не удалась");
            }
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
            } else {
                throw new SQLException("Мы не можем получить ID из БД");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Optional<User> findByLoginAndPassword(String login,String password) {
if(login.equals("")&password.equals("")){return Optional.empty();}
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD);
        ) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("surname"));
                System.out.println(resultSet.getString("login"));
                System.out.println(resultSet.getString("password"));
                System.out.println(resultSet.getString("nickname"));
                System.out.println(resultSet.getLong("id"));

                return  Optional.of(User.builder()
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .login(resultSet.getString("login"))
                        .id(resultSet.getLong("id"))
                        .password(resultSet.getString("password"))
                        .nickname(resultSet.getString("nickname"))
                        .build());
            } else return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<User> findByLogin(String login) {
        // try (
        //           Connection connection=dataSource.getConnection();
        //           PreparedStatement statement= connection.prepareStatement(SELECT);
        // )
        {

        }
        return Optional.empty();
    }

    @Override
    public Optional<User> searchByNameSurname(String name, String surname) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
