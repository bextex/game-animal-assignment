package com.company;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Store {

    Scanner input = new Scanner(System.in);
    Random random = new Random();

    public int animalCost;
    public String animalName;
    public int currentMoney;
    public boolean gender = random.nextBoolean();
    Animal animal;
    Food food;

    public void buyAnimal(Player player) throws Exception {
        System.out.println("What animal do you wanna buy? Cow[1], fish[2], horse[3], rabbit[4], ostrich[5] or exit[6] to get back to menu.");
        String choice = input.nextLine().toLowerCase();
        System.out.println("Type in the name of your animal:");
        this.animalName = input.nextLine();
        String genderOfAnimal = gender ? "female" : "male";
        switch (choice) {
            case "1", "cow" -> this.animal = new Cow(animalName, genderOfAnimal);
            case "2", "fish" -> this.animal = new Fish(animalName, genderOfAnimal);
            case "3", "horse" -> this.animal = new Horse(animalName, genderOfAnimal);
            case "4", "rabbit" -> this.animal = new Rabbit(animalName, genderOfAnimal);
            case "5", "ostrich" -> this.animal = new Ostrich(animalName, genderOfAnimal);
            case "6", "exit" -> {break;}
            default -> throw new Exception("Not a valid choice.");
        }
        player.animals.add(this.animal);
        if (player.money > this.animal.price) {
            player.money = player.money - this.animal.price;
            System.out.println("Congratulations on your buy. You have " + player.money + " left.");
        } else {
            System.out.println("You can't afford to buy " + this.animal);
        }
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
            System.out.println("Congratulations on your buy. You have " + player.money + " left.");
            player.addFood(food, foodInKg);
        } else {
            System.out.println("You can't afford it.");
        }

    }
        





















        /*
        System.out.println("What animal would you like to buy? Cow[1], fish[2], horse[3], rabbit[4] or ostrich[5]?");
        String choice = input.nextLine().toLowerCase();
        System.out.println("Type the name of your animal.");
        String animalName = input.nextLine();
        System.out.println("Type in female[1] or male[2] for gender of your animal.");
        String animalGender = input.nextLine();
        animalGender = animalGender.equals("1") ? "female" : "male";
        if(choice.contains("cow") || choice.contains("1")){
            player.animals.add(new Cow(animalName, animalGender));
        } else if(choice.contains("fish") || choice.contains("2")){
            player.animals.add(new Fish(animalName, animalGender));
        } else if(choice.contains("horse") || choice.contains("3")){
            player.animals.add(new Horse(animalName, animalGender));
        } else if(choice.contains("rabbit") || choice.contains("4")){
            player.animals.add(new Rabbit(animalName, animalGender));
        } else if(choice.contains("ostrich") || choice.contains("5")){
            player.animals.add(new Ostrich(animalName, animalGender));
        } else{
            throw new Exception("Not valid.");
        }
    } */

    /*
    public void buyFood(Player player) throws Exception {
        while(true) {
            System.out.println("What food would you like to buy? Grass[1], corn[2], meat[3] or exit[4]?");
            String choice = input.nextLine().toLowerCase(); // Add try-catch
            if (choice.contains("exit") || choice.contains("4")) {
                break;
            }
            int idPricePerKg;
            System.out.println("How much " + choice + " would you like to buy in kg?");
            int foodInKg = Integer.parseInt(input.nextLine()); // Add try-catch
            if(player.money < (foodInKg * Grass.pricePerKg) || player.money < (foodInKg * Corn.pricePerKg)
                    || player.money < (foodInKg * Meat.pricePerKg)){
                System.out.println("You don't have enough money.");
                break;
            }
            if (choice.contains("grass") || choice.contains("1")) {
                choice = "grass";
                idPricePerKg = Grass.pricePerKg;
                // add "grass" as food if not added, change kg of food the player have
            } else if (choice.contains("corn") || choice.contains("2")) {
                choice = "corn";
                idPricePerKg = Corn.pricePerKg;
            } else if (choice.contains("meat") || choice.contains("3")) {
                choice = "meat";
                idPricePerKg = Meat.pricePerKg;
            } else {
                throw new Exception("Not valid.");
            }
            player.money = player.money - Math.round(foodInKg * idPricePerKg);
            //player.addFood(food, foodInKg);
            System.out.println("You bought " + foodInKg + " kg of " + choice + ". " + "You now have " + player.money + " kr left.");
            sleep(500);
        }

    }
*/
    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        }
        catch(Exception ignore){}
    }



}
