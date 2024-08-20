package ru.rinat.springMvcApp1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rinat.springMvcApp1.models.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM person WHERE id =?", new BeanPropertyRowMapper<>(Person.class), id);
    }

    public void save(Person person) {
        jdbcTemplate.update(
                "INSERT INTO person (id, name, age, email) VALUES (1,?,?,?)",
                person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person newPerson) {
        jdbcTemplate.update(
                "UPDATE person SET name =?, age =?, email =? WHERE id =?",
                newPerson.getName(), newPerson.getAge(), newPerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id =?", id);
    }

//    Test batchUpdate

    public void testMultipleUpdate() {
        List<Person> people = createPeople();

        long start = System.currentTimeMillis();
        for (Person person : people) {
            jdbcTemplate.update(
                    "INSERT INTO person (id, name, age, email) VALUES (?,?,?,?)",
                    person.getId(), person.getName(), person.getAge(), person.getEmail());
        }
        long end = System.currentTimeMillis();
        System.out.println("Multiple update took " + (end - start));
    }

    private List<Person> createPeople() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Person person = new Person();
            person.setId(i);
            person.setName("name" + i);
            person.setAge(i + 20);
            person.setEmail("email" + i + "@gmail.com");
            people.add(person);
        }
        return people;
    }

    public void testBatchUpdate() {
        List<Person> people = createPeople();

        long start = System.currentTimeMillis();
        jdbcTemplate.batchUpdate(
                "INSERT INTO person (id, name, age, email) VALUES (?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, people.get(i).getId());
                        ps.setString(2, people.get(i).getName());
                        ps.setInt(3, people.get(i).getAge());
                        ps.setString(4, people.get(i).getEmail());
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });
        long end = System.currentTimeMillis();
        System.out.println("Batch update took " + (end - start));
    }
}
