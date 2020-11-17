package ru.genndy.restbla.controllers;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextBootstrapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.genndy.restbla.config.SpringConfigTest;
import ru.genndy.restbla.models.Person;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


// @RunWith(SpringRunner.class) // пришлось качать библиотеку SpringRunner.class


//    настроил mock моего сервиса нужным образом;
//    передал его (вероятнее всего, через конструктор) экземпляру использующего его другого класса (предположим, содержащего какую-то бизнес-логику, использующую предоставляемые DataService данные), который я, собственно, и тестировал бы;
//    задействовал функционал тестируемого класса и проконтролировал бы результаты;
//    при необходимости проконтролировал бы количество и порядок вызовов метода(ов) моего mock, которые должны были быть вызваны тестируемым классом в результате предыдущего действия.

// @ExtendWith(SpringExtension.class)
 @ContextConfiguration(classes = SpringConfigTest.class) // мы ведь смогли? Нет
 @WebMvcTest(PeopleController.class) // Создаёт ошибки
public class PeopleControllerTest {
    TestContextBootstrapper asd;
    SpringConfigTest dataServiceMock = Mockito.mock(SpringConfigTest.class);
    private static Person person = mock(Person.class);
    private static String name = "MockTestName";
    private GenericConversionService conversionService;

    @MockBean // Mock - пустышка, чучело, которое скармливатся системе вместо реальных данных с БД
    @Autowired
    private MockMvc mockMvc;

    @Test // Ну вот ща... ща ща ща
    void delete() {

    }

    @Test
    void showPeople() {
    }

    @Before
    public void setup() {
        conversionService.addConverter(new BarToFooConverter());
    }

    @BeforeAll
    public static void getPersonMock(){
        System.out.println("BeforeAll: getPersonMock()");
//        when(person.getId()).then(person.getName()); // Итак, надо достать Mock
    }

    /*
    @Test
    void getPersonById(){
        try {
            System.out.println("Инициализация теста getPersonById(): ");
            ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("people/edit/{id}"));
            result.andExpect(MockMvcResultMatchers.view().name("people/person")) // попробуем для начала без people/
                    .andExpect(MockMvcResultMatchers.model().attributeExists("person"));
        } catch (Exception e){
            // Oh no!
            System.err.println("Тест getPersonById() был остановлен с ошибкой: " +
            e.getMessage());
        }
    }

     */

    @Test
    void edit() {
    }

    @Test
    void patch() {
    }

    @Test
    void newPerson() {
    }

    @Test
    void addPerson() {
    }
}


