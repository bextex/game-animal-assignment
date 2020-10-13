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
    Player playerToTradeWith;
    boolean activeRound = true;

    public void buyAnimal(Player player){
        System.out.println("---- WELCOME TO THE STORE FOR BUYING ANIMALS ----\n");
        do {
            animalSelection(player, false);
            this.animal = player.animals.get(player.animals.size() - 1);
            boolean transactionOK = makeTheTransaction(player, animal.price, true);
            if(!transactionOK){
                player.animals.remove(player.animals.size() - 1);
            }
            activeRound = continueOrExit();
        } while (activeRound);
    }

    public void sellAnimal(Player player){
        System.out.println("---- WELCOME TO THE STORE FOR SELLING ANIMALS ----\n");
        do {
            Animal existAnimal = animalExist(player);
            if(existAnimal != null){
                double cost = existAnimal.price * (existAnimal.health / 100);
                System.out.println("Selling price for " + existAnimal.name + " is: " + cost + " kr.");
                boolean transactionOK = makeTheTransaction(player, cost, false);
                if(transactionOK){
                    player.animals.remove(existAnimal);
                }
            } else {
                System.out.println("You can't sell an animal you don't own.\n");
            }
            activeRound = continueOrExit();
        } while(activeRound);
    }

    public void buyFood(Player player) throws Exception {
        System.out.println("---- WELCOME TO THE STORE FOR BUYING FOOD ---\n");
        do {
            String choice = foodSelection();
            System.out.println("Type in how many kilos of food you want.");
            int foodInKg = 0;
            try {
                foodInKg = Integer.parseInt(Prompt.inputCheck(input.nextLine(), 1, 200));
            } catch (Exception ignore) {}
            if(foodInKg <= 0){
                System.out.println("Type in a number bigger than 0!");
            } else {
                Food food = player.convertStringToFood(choice);
                double cost = food.pricePerKg * foodInKg;
                boolean transactionOK = makeTheTransaction(player, cost, true);
                if(transactionOK){
                    player.addFood(choice, foodInKg);
                }
            }
            activeRound = continueOrExit();
        } while(activeRound);
    }

    public void sellAnimalToPlayer(Player player){
        System.out.println("Which player do you wanna trade with?");
        String playerName = input.nextLine().toLowerCase();
        for(Player p : Player.players){
            if(p.name.toLowerCase().equals(playerName)){
                playerToTradeWith = p;
            }
        }
        if (playerToTradeWith.animals.size() == 0) {
            System.out.println(playerToTradeWith.name + " don't own any animals at the moment.");
        } else {
            System.out.println(playerToTradeWith.name + " owns: ");
            for (Animal a : playerToTradeWith.animals) {
                System.out.println("- " + a.name);
            }
        }


        System.out.println("Do you wanna buy[1] or sell[2]?");
        String choice = Prompt.inputCheck(input.nextLine(), 1, 2);
        boolean buy;
        switch (choice.toLowerCase()){
            case "1", "buy" -> buy = true;
            case "2", "sell" -> buy = false;
            default -> System.out.println("Not a valid choice!");
        }


        animalExist(playerToTradeWith);
        player.animals.add(this.animal);
        playerToTradeWith.animals.remove(this.animal);
        int cost = animal.currentAge * animal.price;
        makeTheTransaction(player, cost, true);
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

    public void animalSelection(Player player, boolean mating){
        String choice;
        String gender;
        if(!mating) {
            System.out.println("Choose cow[1], fish[2], horse[3], rabbit[4] or ostrich[5].");
            choice = Prompt.inputCheck(input.nextLine(), 1, 5);
            gender = genderOfAnimal(true);
        } else {
            choice = this.animal.getClass().getSimpleName().toLowerCase();
            gender = genderOfAnimal(false);
        }
        System.out.println("Type in the name of your new " + gender + (mating ? " baby:" : " animal:"));
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
            String genderChoice = Prompt.inputCheck(input.nextLine().toLowerCase(), 1, 2);
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
        String choice = Prompt.inputCheck(input.nextLine(), 1, 3);
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

    public boolean makeTheTransaction(Player player, double cost, boolean buy){
        if(buy && player.money < cost){
            System.out.println("You can't afford it!\n");
            return false;
        }
        if(buy){
            player.money = (int) (player.money - cost);
        } else {
            player.money = (int) (player.money + cost);
        }
        System.out.println("Congratulations, the transaction was successful! You now have " + player.money + " kr!\n");
        return true;
    }

    public boolean continueOrExit(){
        System.out.println("Do you want to continue[1] or exit[2]?");
        String choice = Prompt.inputCheck(input.nextLine().toLowerCase(), 1, 2);
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
