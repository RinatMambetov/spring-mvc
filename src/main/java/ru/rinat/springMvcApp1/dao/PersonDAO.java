package ru.rinat.springMvcApp1.dao;

import org.springframework.stereotype.Component;
import ru.rinat.springMvcApp1.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int people_count = 0;
    private final List<Person> people;

    public PersonDAO() {
        people = new ArrayList<>();

        people.add(new Person(++people_count, "Ivan"));
        people.add(new Person(++people_count, "Petr"));
        people.add(new Person(++people_count, "Dmitri"));
        people.add(new Person(++people_count, "Sergey"));
        people.add(new Person(++people_count, "Alex"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void save(Person person) {
        person.setId(++people_count);
        people.add(person);
    }

    public void update(int id, Person newPerson) {
        Person oldPerson = show(id);
        oldPerson.setName(newPerson.getName());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
