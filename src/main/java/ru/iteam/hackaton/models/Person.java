package ru.iteam.hackaton.models;

public class Person {
    private String name, email, password, isteacher;

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
