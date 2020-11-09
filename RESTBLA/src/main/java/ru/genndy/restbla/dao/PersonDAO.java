package ru.genndy.restbla.dao;

import org.springframework.stereotype.Component;
import ru.genndy.restbla.databases.ConnectionPostgreSQL;
import ru.genndy.restbla.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int STATICID;
    // PersonDAO позволяет крыть собою основные модели от представления
    List<Person> people = new ArrayList<Person>(); // Надо бы для начала заполнить чем-то

    public PersonDAO(){
        people.add(new Person(++STATICID, "Jackson"));
        people.add(new Person(++STATICID, "Brietney"));
        people.add(new Person(++STATICID, "John"));
        people.add(new Person(++STATICID, "Richard"));

        ConnectionPostgreSQL conn = new ConnectionPostgreSQL();
    }

    public Person getByIndex(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
    public List<Person> getAll(){ //
        return people;
    }

    public void addPerson(Person person){
        people.add(new Person(++STATICID, person.getName()));
        System.out.println("Person has been added: "
                + people.get(people.size()-1).getName() + " "
                + people.get(people.size()-1).getId());
    }
    public void delete(int id){
        System.out.println("Person has been removed");
        people.remove(id - 1);
    }
    public void update(int id, Person newPerson){
        // Пока костыльно
        Person personToBeUpdated = getByIndex(id);

        personToBeUpdated.setName(newPerson.getName());
    }
}
