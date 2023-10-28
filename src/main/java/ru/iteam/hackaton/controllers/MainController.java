package ru.iteam.hackaton.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.iteam.hackaton.dao.PersonDao;
import ru.iteam.hackaton.models.Person;

@Controller
public class MainController {
    @Autowired
    PersonDao personDAO;
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/auth")
    public String auth(){
        return "register.html";
    }

    @PostMapping("/")
    public String saveUser(@ModelAttribute("person")Person person){
        if (person.getName() == null){
            System.out.println("login");
        }
        else {
            personDAO.save(person);
            System.out.println("reg");
        }
        return "redirect:/";
    }
}
