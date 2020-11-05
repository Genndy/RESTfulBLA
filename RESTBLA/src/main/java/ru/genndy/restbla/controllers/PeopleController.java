package ru.genndy.restbla.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.genndy.restbla.dao.PersonDAO;
import ru.genndy.restbla.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    PersonDAO people = new PersonDAO();

    @GetMapping() // Типа по запросу "/people" прилетает
    public String showPeople(Model model) {
        model.addAttribute("people", people.getAll()); // Ну так мы вот передали модель... В чём проблема то??? АААА
        System.out.println(people.getByIndex(1).getName() + " передаёт привет"); // Проверка
        return "pages/people"; // Типа, это представление теперь имеет ту модель...
    }
    @GetMapping("/{id}") // пора бы уже запомнить что фигурные скобки означают прилетающий аргумент
    public String getPersonById(@PathVariable("id") int id, Model model){
        model.addAttribute(people.getByIndex(id));
        return "pages/person"; // А вообще, можно было бы в полноценный профиль переделать
    }
}
