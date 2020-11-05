package ru.genndy.restbla.dao;

import ru.genndy.restbla.models.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    private static int STATICID;
    // PersonDAO позволяет крыть собою основные модели от представления
    List<Person> people = new ArrayList<Person>(); // Надо бы для начала заполнить чем-то

    public PersonDAO(){
        people.add(new Person(++STATICID, "Jackson"));
        people.add(new Person(++STATICID, "Brietney"));
        people.add(new Person(++STATICID, "John"));
        people.add(new Person(++STATICID, "Richard"));
    }

    public Person getByIndex(int i){
        return people.get(i);
    }
    public List<Person> getAll(){ //
        return people;
    }
    private void addPerson(){

    }
    private void deletePersone(){

    }
    private void update(){

    }
}
