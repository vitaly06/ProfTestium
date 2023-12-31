package ru.iteam.hackaton.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.iteam.hackaton.dao.CourseDAO;
import ru.iteam.hackaton.dao.PersonDao;
import ru.iteam.hackaton.models.Course;
import ru.iteam.hackaton.models.Person;

import java.util.List;
import java.util.Objects;

@Controller
public class MainController {
    public boolean isAuth = false;
    public int isTeacher = 0; // 1 - student; 2 - teacher;
    public List<Course> courses;
    String[] data;

    @Autowired
    PersonDao personDAO;
    @Autowired
    CourseDAO courseDAO;

    // Главная страница
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("isAuth", isAuth);
        model.addAttribute("isTeacher", isTeacher);
        return "index";
    }

    @GetMapping("/tarifs")
    public String tarifs() {
        return "tarifs";
    }

    @GetMapping("/courses")
    public String courses(Model model){
        courses = courseDAO.getData();
        for(Course course : courses){
            System.out.println(courseDAO.getId(course));
        }
        model.addAttribute("courses",  courses);
        model.addAttribute("dao", courseDAO);

        return "courses";
    }

    // Регистрация / авторизация
    @GetMapping("/auth")
    public String auth(Model model) {
        model.addAttribute("person", new Person("ishak", "dffd@yandex.ru", "gfgfgf", "gffgfggf"));
        return "register";
    }

    @GetMapping("/lk")
    public String lk(Model model) {
        model.addAttribute("name", data[0]);
        if (Objects.equals(data[3], "teacher")) {
            return "lkAdm";
        }
        return "lkUser";
    }

    @GetMapping("/create_course")
    public String createCourse() {
        return "create_course";
    }

    @GetMapping("/course")
    public String course(Model model){
        return "course";
    }
    @PostMapping()
    public String saveUser(@ModelAttribute("person") @Valid Person person,
                           BindingResult bindingResult) {
        // Логин
        isAuth = false;
        if (person.getName() == null) {
            if (bindingResult.hasErrors()) {
                return "register";
            }
            String password = personDAO.login(person.getEmail(), true);
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
        }
        if (isAuth) {
            data = personDAO.getData(person);
        }
        if (Objects.equals(data[3], "teacher")) {
            isTeacher = 2;
        } else {
            isTeacher = 1;
        }
        System.out.println(isAuth);
        return "redirect:/";
    }
    @PostMapping("/lk")
    public String SaveCourse(@ModelAttribute("course") Course course){
        System.out.println(1);
        courseDAO.save(course);
        return "redirect:/";
    }
}
