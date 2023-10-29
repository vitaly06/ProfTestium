package ru.iteam.hackaton.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.iteam.hackaton.dao.PersonDao;
import ru.iteam.hackaton.models.Person;

import java.util.Objects;

@Controller
public class MainController {
    public boolean isAuth = false;
    public int isTeacher = 0; // 1 - student; 2 - teacher;
    public String manName;
    @Autowired
    PersonDao personDAO;

    // Главная страница
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("isAuth", isAuth);
        model.addAttribute("isTeacher", isTeacher);
        return "index";
    }

    // Регистрация / авторизация
    @GetMapping("/auth")
    public String auth(Model model) {
        model.addAttribute("person", new Person("ishak", "dffd@yandex.ru", "gfgfgf", "gffgfggf"));
        return "register";
    }

    @GetMapping("/lk")
    public String lk(Model model) {
        model.addAttribute("name", manName);
        if (isTeacher == 2) {
            return "lkAdm";
        }
        return "lkUser";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        // Логин
        isAuth = false;
        if (person.getName() == null) {
            if (bindingResult.hasErrors()) {
                System.out.println(bindingResult);
                return "register";
            }
            String password = personDAO.login(person.getEmail(), true);
            System.out.println(password);
            if (Objects.equals(password, person.getPassword())) {
                isAuth = true; // Авторизовано успешно
            } else {
                isAuth = false; // Неверно
            }
        } else {
            if (bindingResult.hasErrors()) {
                return "/auth/";
            }
            String email = personDAO.login(person.getEmail(), false);
            System.out.println(email + ' ' + person.getEmail());
            // Проверка, что почта не занята
            if (!Objects.equals(email, person.getEmail())) {
                if (Objects.equals(person.getSecondPass(), person.getPassword())) {
                    personDAO.save(person);
                    isAuth = true;
                } else {
                    isAuth = false;
                }
            } else {
                isAuth = false;
            }
            //System.out.println("reg");
        }
        if (isAuth) {
            System.out.println(person.getIsteacher());
            if (Objects.equals(person.getIsteacher(), "teacher")) {
                isTeacher = 2;
            }
            else{
                isTeacher = 1;
            }
        }
        manName = person.getName();
        //System.out.println(isAuth);
        return "redirect:/";
    }
}
