package com.example.pizza.repositories;

import com.example.pizza.dtos.UserDto;
import com.example.pizza.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UsersRepository {

    private static String INSERT = "INSERT INTO users (name,surname,login,password,phone,email) values (?,?,?,?,?,?)";
    private static String SELECT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login=? AND password=?";

    private static String SELECT_BY_LOGIN="SELECT * FROM users WHERE login=?";

    private static String SELECT_BY_ID="SELECT * FROM users WHERE id=?";
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
            statement.setString(5, user.getPhone());
            statement.setString(6,user.getEmail());
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

                return  Optional.of(User.builder()
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .login(resultSet.getString("login"))
                        .id(resultSet.getLong("id"))
                        .password(resultSet.getString("password"))
                        .phone(resultSet.getString("phone"))
                        .build());
            } else return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Integer> findByLogin(String login) {
         try (
                   Connection connection=dataSource.getConnection();
                   PreparedStatement statement= connection.prepareStatement(SELECT_BY_LOGIN);

         )
        {
            statement.setString(1,login);
ResultSet rs=statement.executeQuery();
if(rs.next()){
   return Optional.of(rs.getInt("id"));
}
        } catch (SQLException e) {
             throw new RuntimeException(e);
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

    @Override
    public UserDto findById(int id) {
        try (
                Connection connection=dataSource.getConnection();
                PreparedStatement statement= connection.prepareStatement(SELECT_BY_ID);
        )
        {
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            if(rs.next()){
               return UserDto.builder().
                        id(rs.getInt("id")).
                        name(rs.getString("name")).
                        surname(rs.getString("surname")).
                        phone(rs.getString("phone")).
                       email(rs.getString("email")).
                        build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}