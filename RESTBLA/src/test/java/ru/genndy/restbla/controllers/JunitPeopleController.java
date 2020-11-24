package ru.genndy.restbla.controllers;

import com.gargoylesoftware.htmlunit.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.asm.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.htmlunit.LocalHostWebClient;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.ModelAndViewAssert;
import ru.genndy.restbla.dao.interfaces.PersonDAO;
import org.springframework.core.env.StandardEnvironment;
import ru.genndy.restbla.dao.PersonDAOMock;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

// @SpringBootApplication

// @WebMvcTest(controllers = PeopleController.class) // Иииии, что это дало??
// @AutoConfigureMockMvc

// @ExtendWith
// @SpringBootTest(webEnvironment = RANDOM_PORT)
// @SpringBootTest(webEnvironment = RANDOM_PORT)

// Нужен чтобы сработал Autowired testRestTemplate
// Сам вызывает ошибки

// @SpringBootTest(webEnvironment = MOCK)
// @ExtendWith(SpringExtension.class)
//  @SpringBootTest(webEnvironment = RANDOM_PORT)
// @WebMvcTest(controllers = PeopleController.class) // Иииии, что это дало??
// @ContextConfiguration
 @ExtendWith(SpringExtension.class)
// @WebMvcTest(PeopleController.class)
class JunitPeopleController {

//    @Autowired
    PeopleController peopleController;
    PersonDAO people;
    LocalHostWebClient localHostWebClient;

    @Autowired(required = true)

    public JunitPeopleController() throws IOException {
        StandardEnvironment environment = new StandardEnvironment(); // Шо это?! Это шо такое?!
        localHostWebClient = new LocalHostWebClient(environment);
        people = new PersonDAOMock();
        peopleController = new PeopleController(); // Таким образом, мы не создаём по новой контроллер,
//        а используем то, что достаётся нам от Tomcat
        peopleController.setPersonDAO(people);

    }

    @Test
    public void getAllPeople() throws IOException {
        Page page = localHostWebClient.getPage("http://localhost:8081/people");
        File file = new File("RESTBLA/src/main/webapp/WEB-INF/views/people/all.html");
        assertEquals(true, page.isHtmlPage());  // А страница ли это вообще? )))
    }

    @Test
    void getPersonById() throws IOException {
        // Отправить запрос на получение... Что я ожидаю от данной команды?
        // For first let's checkk page:
        Page page = localHostWebClient.getPage("http://localhost:8081/people/1");
        assertEquals(true, page.isHtmlPage());
        // Ok, let's see how we can take model...
    }


    @Test
    public void patch() throws Exception {
            MockHttpServletRequest req = new MockHttpServletRequest("POST", "http://localhost:8081/update/{id}");
            req.setParameter("name","New name");

              Page page = localHostWebClient.getPage("http://localhost:8081/update/1");
    }

    @Test
    public void addPerson() throws Exception {
//        req.setParameter("name","Test");
//        req.setParameter("duration","45");
        // req = new MockHttpServletRequest("POST", "/vote");

//        List<Person> testPeopleBefore = people.getAll();
//        Person mockPerson = new Person(435, "Dart Testus");
//        PeopleController


    }

    @Test
    void showPeople() {
    }

    @Test
    void edit() {
    }

    @Test
    void newPerson() {
    }

    @Test
    void delete() {
    }
}



// Позже понадобиться, когда понадобиться тестировать соединение с мобилкой
//        testRestTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(new MockEnvironment(), "http", new RootUriTemplateHandler("://localhost:8081")));
//        testRestTemplate.setUriTemplateHandler();
//        testRestTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8081"));