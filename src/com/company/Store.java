package com.company;

import java.util.ArrayList;

public class Store {

    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<Animal> animals;
    public static ArrayList<Food> foods;

    public int animalCost;
    public String animalName;

    public void buyAnimal(Player player, Animal animal){
        if(player.money > animalCost) {
            //animals.add(player);
            //players.add(animal);
            System.out.println("You have now successfully bought");
        }
    }

    public void buyFood(Player player, Food food){

    }

}
