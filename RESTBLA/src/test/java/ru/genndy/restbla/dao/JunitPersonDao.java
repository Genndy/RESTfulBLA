package ru.genndy.restbla.dao;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;


// @ContextConfiguration()
 @Transactional
// @RunWith(SpringJUnit4ClassRunner.class)
// @SpringBootTest
class JunitPersonDao {
   // @Autowired
    private PersonDAOImpl personDAO = new PersonDAOImpl(); // Возможно будет ругаться

 //   @Autowired
//    private DepartmentDAO departmentDAO;


    @Test
    @Transactional
//    @Rollback(true) // То есть, возвращаемое значение?
    public void testAddPerson()
    {

        /*
        Person person = new Person(0 ,"Gerald");
        personDAO.addPerson(person);
        List<Person> peopleTest = personDAO.getAll();
        Assert.assertEquals(1, peopleTest.size());

        Assert.assertEquals(personDAO.getByIndex(0).getName(), peopleTest.get(0).getName()); // А у меня же идэшники выдаются рандомно! ААААААА....
        System.out.println("testAddPerson gets: " +personDAO.getByIndex(0).getName() + " and " + peopleTest.get(0).getName());
         */
    }

    @Test
    void getByIndex() { //Стоит ли это тестировать?...
    }

    @Test
    void getAll() {

    }

    @Test
    void getUnicIdForPerson() {
    }

    @Test
    void addPerson() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}