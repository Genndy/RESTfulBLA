package ru.genndy.restbla.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.genndy.restbla.dao.interfaces.PersonDao;
import ru.genndy.restbla.models.Person;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
// extends JdbcDaoSupport

public class PersonDAOImpl implements PersonDao {

    //   private JdbcTemplate jdbcTemplate; // Го короче прямо тут организуем подключение к Postgres...

    //   @Autowired
//    private DataSource dataSource;
/*
    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }
*/

    private static int STATICID;
    // PersonDAO позволяет крыть собою основные модели от представления
    List<Person> people = new ArrayList<Person>(); // Надо бы для начала заполнить чем-то
    NamedParameterJdbcTemplate template;

    public PersonDAOImpl(){
        System.out.println("PersonDAO has been created");

        people.add(new Person(++STATICID, "Jackson"));
        people.add(new Person(++STATICID, "Brietney"));
        people.add(new Person(++STATICID, "John"));
        people.add(new Person(++STATICID, "Richard"));

        //      ConnectionPostgreSQL conn = new ConnectionPostgreSQL();
    }

    public PersonDAOImpl(NamedParameterJdbcTemplate template){
        System.out.println("PersonDAO has been created");
        this.template = template;
    }

    public Person getByIndex(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
    public List<Person> getAll(){ //
        return people;
    }

    public void addPerson(Person person){
        /*
        people.add(new Person(++STATICID, person.getName()));
        System.out.println("Person has been added: "
                + people.get(people.size()-1).getName() + " "
                + people.get(people.size()-1).getId());

         */
        final String = "INSERT into PERSONS"

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", person.getId())
                .addValue("name", person.getName());
        template.update(sql, param, holder);
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
    /*
    @Override
    protected JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Override
    protected void initTemplateConfig() {
    }
    */

}
