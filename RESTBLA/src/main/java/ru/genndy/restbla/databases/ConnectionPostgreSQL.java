package ru.genndy.restbla.databases;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;
import ru.genndy.restbla.models.Person;

import javax.ejb.Singleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
@Singleton
public class ConnectionPostgreSQL {
    public ConnectionPostgreSQL() {
        System.out.println("Spring достучался досюда таки");
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUsername("postgres"); // Потом поменять
//        dataSource.setUrl("jdbc:h2:mem");
        dataSource.setUrl("127.0.0.1");
        dataSource.setPassword("4a7bda"); // Особенно это поменять

        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        // Проверка на наличие базы данных/Инициализация базы данных
        List<Integer> isTableExist = jdbc.query("IF OBJECT_IDIF EXISTS (SELECT 1 \n" +
                "           FROM INFORMATION_SCHEMA.TABLES \n" +
                "           WHERE TABLE_TYPE='BASE TABLE' \n" +
                "           AND TABLE_NAME='PERSONS') \n" +
                "   SELECT 1 AS res ELSE SELECT 0 AS res;", ResultSet::getInt);

        if (isTableExist.get(0) == 1) {
            System.out.println("Table already exist");
        } else {
            System.err.println("Table doesn't exist");
            System.out.println("Creating tables");
            // jdbcTemplate.execute("drop table customers if exists"); // пусть повисит тут
            jdbc.execute("create table PERSONS(" +
                    "id serial, name varchar(255)"); // table создан...

            String[] names = "John Woo;Jeff Dean;Josh Bloch;Josh Long".split(";");
//            for (String fullname : names) {
            // String[] name = fullname.split(" "); // гениальный метод. Надо запомнить...
            System.out.printf("Inserting persons record for %s %s\n", names[0], names[1], names[2]);

//            jdbc.update(names = 'Josh':");
            List<Person> personList = jdbc.query("select * from persons where name = ?", new Object[]{"josh"}
            ,new RowMapper<Person>(){
                @Override
                public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Person(rs.getInt("id"), rs.getString("name"));
                }
            });
            for(Person person : personList){
                System.out.println(person.getName());
            }
        }
..
    }
}
/*
        System.out.println("Querying for customer records where
*/
