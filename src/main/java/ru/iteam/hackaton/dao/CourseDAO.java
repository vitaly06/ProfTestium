package ru.iteam.hackaton.dao;

import org.springframework.stereotype.Component;
import ru.iteam.hackaton.models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<Course> getData(){
        connect();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM COURSES";
            List<Course> courses = new ArrayList<Course>();
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next())
            {
                courses.add(new Course(
                resultSet.getString("text"),
                resultSet.getString("short_info")));
            }
            //System.out.println(name + " " + email + " " + password + " " + isteacher);
            connection.close();
            return courses;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    public int getId(Course course){
        connect();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT id FROM courses WHERE short_info = '" + course.getShort_info() + "';";
            ResultSet resultSet = statement.executeQuery(SQL);
            int id = resultSet.getInt("id");
            //System.out.println(name + " " + email + " " + password + " " + isteacher);
            connection.close();
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

}
