package ru.genndy.restbla.models;

public class Person {
    private int identity; // id потом лучше делать в виде строки
    private String name;

    public Person(){

    }

    public int getId() {
        return identity;
    }

    public void setId(int identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int identity, String name) {
        this.identity = identity;
        this.name = name;
    }
}
