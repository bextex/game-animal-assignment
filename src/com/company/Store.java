package com.company;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Store {

    Scanner input = new Scanner(System.in);
    Random random = new Random();

    String animalName, gender;
    Animal animal;
    Food food;
    boolean activeRound = true;

    public void buyAnimal(Player player){
        System.out.println("----WELCOME TO THE STORE FOR BUYING ANIMALS----\n");
        do {
            animalSelection(player);
            this.animal = player.animals.get(player.animals.size() - 1);
            makeTheTransaction(player, animal.price, true);
            activeRound = continueOrExit();
        } while (activeRound);
    }

    public void sellAnimal(Player player){
        System.out.println("----WELCOME TO THE STORE FOR SELLING ANIMALS----\n");
        do {
            Animal existAnimal = animalExist(player);
            if(existAnimal != null){
                int cost = (existAnimal.price * (existAnimal.health / 100));
                makeTheTransaction(player, cost, false);
                player.animals.remove(existAnimal);
            } else {
                System.out.println("You can't sell an animal you don't own.\n");
            }
            activeRound = continueOrExit();
        } while(activeRound);
    }

    public void buyFood(Player player) throws Exception {
        System.out.println("----WELCOME TO THE STORE FOR BUYING FOOD----\n");
        do {
            String choice = foodSelection();
            System.out.println("Type in how many kilos of food you want.");
            int foodInKg = 0;
            try {
                foodInKg = Integer.parseInt(input.nextLine());
            } catch (Exception ignore) {}
            if(foodInKg <= 0){
                System.out.println("Type in a number bigger than 0!");
            } else {
                player.addFood(choice, foodInKg);
                Food food = player.convertStringToFood(choice);
                int cost = food.pricePerKg * foodInKg;
                makeTheTransaction(player, cost, true);
            }
            activeRound = continueOrExit();
        } while(activeRound);
    }

    public Animal animalExist(Player player){
        System.out.println("Type in the animals name:");
        this.animalName = input.nextLine().toLowerCase();
        for(int i = 0; i < player.animals.size(); i++){
            if(animalName.equals(player.animals.get(i).name.toLowerCase())){
                this.animal = player.animals.get(i);
                return this.animal;
            }
        }
        return this.animal = null;
    }

    public void animalSelection(Player player){
        System.out.println("Choose cow[1], fish[2], horse[3], rabbit[4] or ostrich[5].");
        String choice = input.nextLine();
        String gender = genderOfAnimal(true);
        System.out.println("Type in the name of your new " + gender + " animal:");
        String nameOfAnimal = input.nextLine();
        switch (choice){
            case "1", "cow" -> player.animals.add(new Cow(nameOfAnimal, gender));
            case "2", "fish" -> player.animals.add(new Fish(nameOfAnimal, gender));
            case "3", "horse" -> player.animals.add(new Horse(nameOfAnimal, gender));
            case "4", "rabbit" -> player.animals.add(new Rabbit(nameOfAnimal, gender));
            case "5", "ostrich" -> player.animals.add(new Ostrich(nameOfAnimal, gender));
            default -> System.out.println("Not a valid choice!");
        }
    }

    public String genderOfAnimal(boolean choosingGender){
        if(choosingGender){
            System.out.println("Choose female[1] or male[2]");
            String genderChoice = input.nextLine().toLowerCase();
            switch (genderChoice){
                case "1", "female" -> gender = "female";
                case "2", "male" -> gender = "male";
                default -> {System.out.println("Not a valid choice!"); activeRound = false;}
            }
        } else {
            int randomGender = random.nextInt(2);
            switch (randomGender){
                case 0 -> gender = "female";
                case 1 -> gender = "male";
                default -> System.out.println("Unsuspected problems for automatic gender reveal!");
            }
        }
        return gender;
    }

    public String foodSelection(){
        System.out.println("Choose grass[1], corn[2] or meat[3].");
        String choice = input.nextLine();
        switch (choice){
            case "1", "grass" -> choice = "grass";
            case "2", "corn" -> choice = "corn";
            case "3", "meat" -> choice = "meat";
            default -> System.out.println("Not a valid choice!");
        }
        return choice;
    }

    private boolean exit(){
        return true;
    }

    public void makeTheTransaction(Player player, int cost, boolean buy){
        if(player.money < cost){
            System.out.println("You can't afford it!");
            return;
        }
        if(buy){
            player.money = player.money - cost;
        } else {
            player.money = player.money + cost;
        }
        System.out.println("Congratulations on your buy! You have now have " + player.money + " kr!");
    }

    public boolean continueOrExit(){
        System.out.println("Do you want to continue[1] or exit[2]?");
        String choice = input.nextLine().toLowerCase();
        boolean continueRound;
        switch (choice){
            case "1", "continue" -> continueRound = true;
            default -> continueRound = false;
        }
        return continueRound;
    }

    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        }
        catch(Exception ignore){}
    }



}
