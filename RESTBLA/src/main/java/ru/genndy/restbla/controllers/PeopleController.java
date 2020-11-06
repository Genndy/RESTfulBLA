package ru.genndy.restbla.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.genndy.restbla.dao.PersonDAO;
import ru.genndy.restbla.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    //PersonDAO people = new PersonDAO();
    final PersonDAO people;

    @Autowired
    public PeopleController(PersonDAO persondAO) {
        this.people = persondAO;
    }

    @GetMapping() // Типа по запросу "/people" прилетает
    public String showPeople(Model model) {
        model.addAttribute("people", people.getAll()); // Ну так мы вот передали модель... В чём проблема то??? АААА
        System.out.println("people showed");
        return "people/all"; // Типа, это представление теперь имеет ту модель...
    }
    @GetMapping("/{id}") // пора бы уже запомнить что фигурные скобки означают прилетающий аргумент
    public String getPersonById(@PathVariable("id") int id, Model model){
        model.addAttribute("person", people.getByIndex(id));
        return "people/person"; // А вообще, можно было бы в полноценный профиль переделать
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", people.getByIndex(id));
        return "people/edit";
    }

    // А вот че было сделано на трезвую голову и работает. Ведь в отладчике мозиллы можно увидеть, что передается name и _method=PATCH
    @PatchMapping("/update/{id}")
    public String patch(@RequestParam(name = "name", required = false) String name, @PathVariable("id") int id) {
        Person p = new Person(id, name);
        System.err.println(name);
        System.err.println(id);
        people.update(id, p);
        System.err.println("submit executed!");
        return "redirect:/people";
    }
    @GetMapping("/new")
    //(@ModelAttribute("person") Person person
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }
    @PostMapping("/new")
    public String addPerson(@ModelAttribute("person") Person person){
        people.addPerson(person);
        return "redirect:/people";
    }

    @DeleteMapping("/delete/{id}") // пока не работает...
    public String delete(@PathVariable("id") int id){
        people.delete(id);
        return "redirect:/people";
    }

    // Шо там ещё было? добавление и удаление. Редактирование
}
