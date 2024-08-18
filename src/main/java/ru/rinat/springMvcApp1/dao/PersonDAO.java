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

        people.add(new Person(++people_count, "Ivan", 21, "ivan@mail.ru"));
        people.add(new Person(++people_count, "Petr", 22, "petr@mail.ru"));
        people.add(new Person(++people_count, "Dmitri", 23, "dmitri@mail.ru"));
        people.add(new Person(++people_count, "Sergey", 24, "sergey@mail.ru"));
        people.add(new Person(++people_count, "Alex", 25, "alex@mail.ru"));
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
        oldPerson.setAge(newPerson.getAge());
        oldPerson.setEmail(newPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
