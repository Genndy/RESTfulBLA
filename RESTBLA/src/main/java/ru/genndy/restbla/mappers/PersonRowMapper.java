package ru.genndy.restbla.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.genndy.restbla.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        return person;
    }
}
