package ru.iteam.hackaton.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message = "Длина имени от 2 до 30 символов.")
    private String name;
    @NotEmpty(message = "Поле email не может быть пустым")
    @Email(message = "Неверный формат")
    private String email;
    private String password;
    private String isteacher;

    public Person(String name, String email, String password, String isteacher) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isteacher = isteacher;
    }

    public String getIsteacher() {
        return isteacher;
    }

    public void setIsteacher(String isteacher) {
        this.isteacher = isteacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
