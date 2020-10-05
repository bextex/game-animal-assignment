package com.company;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Store {

    Scanner input = new Scanner(System.in);
    Random random = new Random();

    String animalName = "";
    Animal animalBought, animal;
    Food food;
    boolean activeRound = true;

    public void buyAnimal(Player player) throws Exception {
        do {
            System.out.println("What animal do you wanna buy? Cow[1], fish[2], horse[3], rabbit[4], ostrich[5] or exit[6] to get back to menu.");
            String choice = input.nextLine().toLowerCase();
            System.out.println("Type in the name of your animal:");
            this.animalName = input.nextLine();
            System.out.println("Choose your animals gender - female[1] or male[2].");
            String genderOfAnimal = input.nextLine().toLowerCase();
            switch (genderOfAnimal) {
                case "1", "female" -> genderOfAnimal = "female";
                case "2", "male" -> genderOfAnimal = "male";
                default -> throw new Exception("Not a valid choice.");
            }
            switch (choice) {
                case "1", "cow" -> animalBought = new Cow(animalName, genderOfAnimal);
                case "2", "fish" -> animalBought = new Fish(animalName, genderOfAnimal);
                case "3", "horse" -> animalBought = new Horse(animalName, genderOfAnimal);
                case "4", "rabbit" -> animalBought = new Rabbit(animalName, genderOfAnimal);
                case "5", "ostrich" -> animalBought = new Ostrich(animalName, genderOfAnimal);
                case "6", "exit" -> {
                    break;
                }
                default -> throw new Exception("Not a valid choice.");
            }
            player.animals.add(animalBought);
            if (player.money > this.animalBought.price) {
                player.money = player.money - this.animalBought.price;
                System.out.println("Congratulations on your buy. You have " + player.money + " kr left.\n");
            } else {
                System.out.println("You can't afford to buy " + this.animalBought + "\n");
            }
            System.out.println("Do you wanna buy more[1] or exit your round[2]?");
            String nextStep = input.nextLine().toLowerCase();
            switch (nextStep){
                case "1", "more" -> activeRound = true;
                case "2", "exit" -> activeRound = false;
            }
        } while (activeRound);
    }

    public void sellAnimal(Player player){
        do {
            System.out.println("Type in the name of your animal you want to sell.");
            this.animalName = input.nextLine().toLowerCase();
            for (int i = 0; i < player.animals.size(); i++) {
                if (animalName.equals(player.animals.get(i).name.toLowerCase())) {
                    this.animal = player.animals.get(i);
                    player.animals.remove(animal);
                    player.money = player.money + (animal.price * (animal.health / 100));
                    System.out.println("Congratulations on your sell. You now have " + player.money + " kr!\n");
                } else {
                    System.out.println("You can't sell an animal you don't own.\n");
                }
            }
            System.out.println("Do you wanna sell more[1] or exit your round[2]?");
            String nextStep = input.nextLine().toLowerCase();
            switch (nextStep){
                case "1", "more" -> activeRound = true;
                case "2", "exit" -> activeRound = false;
            }
        } while(activeRound);
    }

    public void buyFood(Player player) throws Exception {
        do {
            System.out.println("What food would you like to buy? Grass[1], corn[2], meat[3] or exit[4] to get back to menu.");
            String choice = input.nextLine().toLowerCase();
            System.out.println("Typ in how many kilos of food you want.");
            int foodInKg = 0;
            try {
                foodInKg = Integer.parseInt(input.nextLine());
            } catch (Exception ignore) {
            }
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
            System.out.println("Do you wanna buy more[1] or exit your round[2]?");
            String nextStep = input.nextLine().toLowerCase();
            switch (nextStep){
                case "1", "more" -> activeRound = true;
                case "2", "exit" -> activeRound = false;
            }
        } while(activeRound);
    }

    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        }
        catch(Exception ignore){}
    }



}
