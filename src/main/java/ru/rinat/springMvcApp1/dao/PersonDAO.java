package ru.rinat.springMvcApp1.dao;

import org.springframework.stereotype.Component;
import ru.rinat.springMvcApp1.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USER = "rinat";
    private static final String PASSWORD = "password";
    private static int people_count = 0;

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person show(int id) {
        return null;
    }

    public void save(Person person) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO person VALUES (" +
                    "'" + ++people_count + "'," +
                    "'" + person.getName() + "'," +
                    "'" + person.getAge() + "'," +
                    "'" + person.getEmail() + "')";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person newPerson) {
//        Person oldPerson = show(id);
//        oldPerson.setName(newPerson.getName());
//        oldPerson.setAge(newPerson.getAge());
//        oldPerson.setEmail(newPerson.getEmail());
    }

    public void delete(int id) {
//        people.removeIf(person -> person.getId() == id);
    }
}
