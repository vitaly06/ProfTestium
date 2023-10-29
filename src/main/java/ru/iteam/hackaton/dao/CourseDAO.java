package ru.iteam.hackaton.dao;

import org.springframework.stereotype.Component;
import ru.iteam.hackaton.models.Course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class CourseDAO {
    public CourseDAO(){}
    private static Connection connection;

    public static void connect(){
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
    public void save(Course course) {
        connect();
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO COURSES (text, short_info) VALUES('" + course.getText() + "', '" + course.getShort_info() + "')";
            statement.executeUpdate(SQL);
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //System.out.println(1);
    }

}
