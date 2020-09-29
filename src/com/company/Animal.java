package com.company;

public abstract class Animal { // Animal class is abstract and cannot be instantiated

    private String name;
    private String gender;
    private int health;

    public Animal(String name, String gender, int health){
        this.name = name;
        this.gender = gender;
        this.health = health;
    }

}
