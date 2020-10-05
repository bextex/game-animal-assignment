package com.company;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Store {

    Scanner input = new Scanner(System.in);
    Random random = new Random();

    public int animalCost;
    public int currentMoney;

    String animalName = "";
    Animal animalBought, animal;
    Food food;

    public void buyAnimal(Player player) throws Exception {
        System.out.println("What animal do you wanna buy? Cow[1], fish[2], horse[3], rabbit[4], ostrich[5] or exit[6] to get back to menu.");
        String choice = input.nextLine().toLowerCase();
        System.out.println("Type in the name of your animal:");
        this.animalName = input.nextLine();
        boolean gender = random.nextBoolean();
        String genderOfAnimal = gender ? "female" : "male";
        switch (choice) {
            case "1", "cow" -> animalBought = new Cow(animalName, genderOfAnimal);
            case "2", "fish" -> animalBought = new Fish(animalName, genderOfAnimal);
            case "3", "horse" -> animalBought = new Horse(animalName, genderOfAnimal);
            case "4", "rabbit" -> animalBought = new Rabbit(animalName, genderOfAnimal);
            case "5", "ostrich" -> animalBought = new Ostrich(animalName, genderOfAnimal);
            case "6", "exit" -> {break;}
            default -> throw new Exception("Not a valid choice.");
        }
        player.animals.add(animalBought);
        if (player.money > this.animalBought.price) {
            player.money = player.money - this.animalBought.price;
            System.out.println("Congratulations on your buy. You have " + player.money + " kr left.\n");
        } else {
            System.out.println("You can't afford to buy " + this.animalBought +"\n");
        }
    }

    public void sellAnimal(Player player){
        System.out.println("Type in the name of your animal you want to sell.");
        this.animalName = input.nextLine();
        for (int i = 0; i < player.animals.size(); i++) {
            if (animalName.equals(player.animals.get(i).name)) {
                this.animal = player.animals.get(i);
                player.animals.remove(animal);
                player.money = player.money + (animal.price * animal.health);
            } else {
                System.out.println("You can't sell an animal you don't own.\n");
            }
        }
        System.out.println("Congratulations on your sell. You now have " + player.money + " kr!\n");
    }

    public void buyFood(Player player) throws Exception {
        System.out.println("What food would you like to buy? Grass[1], corn[2], meat[3] or exit[4] to get back to menu.");
        String choice = input.nextLine().toLowerCase();
        System.out.println("Typ in how many kilos of food you want.");
        int foodInKg = 0;
        try {
            foodInKg = Integer.parseInt(input.nextLine());
        } catch (Exception ignore) {}
        switch (choice) {
            case "1", "grass" -> this.food = new Grass();
            case "2", "corn" -> this.food = new Corn();
            case "3", "meat" -> this.food = new Meat();
            case "4", "exit" -> {
                break;
            }
            default -> throw new Exception("Not a valid choice.");
        }
        if (player.money > food.pricePerKg * foodInKg) {
            player.money = player.money - food.pricePerKg * foodInKg;
            System.out.println("Congratulations on your buy. You have " + player.money + " kr left.\n");
            player.addFood(food, foodInKg);
        } else {
            System.out.println("You can't afford it.\n");
        }

    }

    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        }
        catch(Exception ignore){}
    }



}
