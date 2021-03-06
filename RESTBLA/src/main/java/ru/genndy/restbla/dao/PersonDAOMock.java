package ru.genndy.restbla.dao;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import ru.genndy.restbla.dao.interfaces.PersonDAO;
import ru.genndy.restbla.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@SpringBootConfiguration
public class PersonDAOMock implements PersonDAO {
    List<Person> people = new ArrayList<Person>();
    SimpleDriverDataSource dataSource;
    JdbcTemplate template;
    Boolean isTableExist;

    public PersonDAOMock(){
        dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUsername("postgres");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/mockDataBase");
        dataSource.setPassword("qwerty"); // Особенно это поменять

        System.out.println("PersonDAO has been created");
    //    this.template = template;
        this.template = new JdbcTemplate(dataSource);
        // Инициализация таблицы
        template.execute("CREATE TABLE IF NOT EXISTS public.persons" +
                " (id serial, name char(255))"); // varchar(255) не позволяет писать кавычки... Ну и ладно. Вроде имена не должны содердать кавычки?
        // Очистить базу данных
        template.execute("delete from persons");
        // Заполняем базу данных mock данными
        String [] names = {"James", "Adam", "Kira", "Grey", "Another random guy"};
        // Надо бы научится уже пользоваться мультизапросом...
        String sqlInsert = "INSERT into persons (id, name) values (";
        for (int i = 1; i < (names.length); i++ ){
            template.execute(sqlInsert + i +", \'" + names[i] +"\')");
        }
    }

    public Person getByIndex(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public List<Person> getAll(){
        String sql = "SELECT * FROM persons";
        List<Map<String, Object>> rows = template.queryForList(sql);
        List<Person> result = new ArrayList<>();
        for(Map<String, Object> row:rows){
            Person person = new Person();
            person.setId((Integer) row.get("id"));
            person.setName((String)row.get("name"));
            result.add(person);
        }
        people = result;
        return people;
    }

    public void addPerson(Person person){ // Готово?
        final String sql= "INSERT into PERSONS (id, name) VALUES ";
        int newId = getUnicIdForPerson();
        try{
            template.execute(sql + "(" + newId + ", \'" + person.getName() +"\')");
            people.add(person); // Чтобы в базу лишний раз не лезть
        }catch (Exception ex){ // Надо бы уточнить
            System.err.println("Написать что-нибудь басурманское и логичное");
        }
    }
    public void delete(int id){
        String sql = "DELETE FROM persons where id = ";
        try{        // Есть опасность десинхронизации
            template.execute(sql + (id));
            people.remove(id - 1);
            System.out.println("Person has been removed");
        }catch (Exception ex){
            System.err.println("Написать что-нибудь басурманское и логичное");
        }
    }
    public void update(int id, Person newPerson){
        // Пока костыльно
        String sql = "UPDATE persons SET name = \'" + newPerson.getName() + "\' WHERE id = " + (id);
        Person personToBeUpdated = getByIndex(id);
        template.execute(sql);
        personToBeUpdated.setName(newPerson.getName());
    }

    private int getUnicIdForPerson(){
        int newId = (int) ((Math.random() * (10000-0)) + 0);
        for(int i = 0; i < people.size(); i++){
            if (newId == people.get(i).getId()){
                return getUnicIdForPerson();
            }
        }
        return newId;
    }

}
