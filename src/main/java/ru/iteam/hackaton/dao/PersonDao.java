package ru.iteam.hackaton.dao;

import org.springframework.stereotype.Component;
import ru.iteam.hackaton.models.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
        //System.out.println(person.getName() + ":" + person.getEmail() + ":" + person.getPassword());
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
}
