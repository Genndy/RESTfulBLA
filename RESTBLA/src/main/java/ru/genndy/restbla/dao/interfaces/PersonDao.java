package ru.genndy.restbla.dao.interfaces;

import ru.genndy.restbla.models.Person;

import java.util.List;

public interface PersonDao {

    public Person getByIndex(int id);

    public List<Person> getAll();

    public void addPerson(Person person);
    public void delete(int id);
    public void update(int id, Person newPerson);

}
