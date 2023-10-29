package ru.iteam.hackaton.dao;

import org.springframework.stereotype.Component;
import ru.iteam.hackaton.models.Person;

import java.sql.*;

@Component
public class PersonDao {
    public PersonDao() {
    }

    private static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\SU\\IdeaProjects\\ITeamOkei" +
                    "\\src\\main\\resources\\users.s3db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void save(Person person) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO USERS VALUES('" + person.getName() + "', '" + person.getEmail() +
                    "', '" + person.getPassword() + "', '" + person.getIsteacher() + "')";
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //System.out.println(1);
    }

    // Авторизация / регистрация (проверка наличия пользователя в базе данных)
    public String login(String email, Boolean isLogin) {
        try {
            Statement statement = connection.createStatement();
            // При авторизации
            if (isLogin) {
                String SQL = "SELECT password FROM USERS WHERE email = '" + email + "'";
                ResultSet resultSet = statement.executeQuery(SQL);
                String password = resultSet.getString("password");
                return password;
            }
            // При регистрации
            String SQL = "SELECT email FROM USERS WHERE email = '" + email + "'";
            ResultSet resultSet = statement.executeQuery(SQL);
            String emailRes = resultSet.getString("email");
            return emailRes;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "f";
    }

}
