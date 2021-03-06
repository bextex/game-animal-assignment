package com.company;

import java.util.Random;
import java.util.Scanner;

public class Store {

    Scanner input = new Scanner(System.in);
    Random random = new Random();

    String animalName, gender;
    Animal animal;
    Player playerToTradeWith;
    boolean activeRound = true;
    boolean tradingPlayerHaveAnimals, playerHaveAnimals, anotherTurn;


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
            if(existAnimal != null) {
                double cost = existAnimal.price * (existAnimal.health / 100) - existAnimal.currentAge;
                System.out.println("Selling price for " + existAnimal.name + " is: " + (int)cost + " kr.");
                boolean selling = yesOrNo();
                if (selling) {
                    boolean transactionOK = makeTheTransaction(player, cost, false);
                    if (transactionOK) {
                        player.animals.remove(existAnimal);
                    }
                }
            } else {
                System.out.println("You can't sell an animal you don't own.\n");
            }
            activeRound = continueOrExit();
        } while(activeRound);
    }

    public void buyFood(Player player) {
        System.out.println("---- WELCOME TO THE STORE FOR BUYING FOOD ----\n");
        do {
            System.out.println("Grass 10 kr/kg | Corn 12 kr/kg | Meat 15 kr/kg\n");
            String choice = foodSelection();
            System.out.println("Type in how many kilos of food you want.");
            int foodInKg = Integer.parseInt(Prompt.inputCheck(input.nextLine(), 1, 200));
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

    public void sellAnimalToPlayer(Player player) {
        do {
            playerToTradeWith = null;
            System.out.println("Which player do you wanna trade with?");
            String playerName = input.nextLine().toLowerCase();
            for (Player p : Player.players) {
                if (p.name.toLowerCase().equals(playerName)) {
                    playerToTradeWith = p;
                }
            }
            if(playerToTradeWith == null || playerToTradeWith == player){
                System.out.println("You can't trade with " + (playerToTradeWith == null ? "a non-existing player." : "yourself.")
                        + "This will cost you your turn. Keep in mind your fellow players names next time.");
                return;
            }
            System.out.println("---- WELCOME TO THE TRADING PLACE ----\n");
            if (player.animals.size() == 0) {
                System.out.println(player.name + " don't own any animals.");
                playerHaveAnimals = false;
            } else {
                System.out.println(player.name + "s animals:");
                for (Animal a : player.animals) {
                    System.out.println("- " + a.name + ", " + (int)(a.price * (a.health / 100) - a.currentAge) + " kr");
                    playerHaveAnimals = true;
                }
            }
            System.out.println();
            if (playerToTradeWith.animals.size() == 0) {
                System.out.println(playerToTradeWith.name + " don't own any animals.");
                tradingPlayerHaveAnimals = false;
            } else {
                System.out.println(playerToTradeWith.name + "s animals:");
                for (Animal a : playerToTradeWith.animals) {
                    System.out.println("- " + a.name + ", " + (int)(a.price * (a.health / 100) - a.currentAge) + " kr");
                    tradingPlayerHaveAnimals = true;
                }
            }
            do{
            System.out.println("\nDo you want to sell[1], buy[2] or exit[3]?");
            String choice = Prompt.inputCheck(input.nextLine(), 1, 3);
            switch (choice) {
                case "1" -> {
                    if (!playerHaveAnimals) {
                        System.out.println("You don't have any animals to sell!");
                        anotherTurn = true;
                    } else {
                        animalExist(player);
                        if (playerToTradeWith.money < (animal.price * (animal.health / 100) - animal.currentAge)) {
                            System.out.println("Yes you can force your opponent to buy, but they still have to have the money to pay for it.");
                            System.out.println("And your opponent don't!");
                            anotherTurn = true;
                        } else {
                            player.animals.remove(this.animal);
                            playerToTradeWith.animals.add(this.animal);
                            double cost = (animal.price * (animal.health / 100) - animal.currentAge) > 0 ? (animal.price * (animal.health / 100) - animal.currentAge) : 0;
                            System.out.println(player.name + ",");
                            makeTheTransaction(player, cost, false);
                            System.out.println(playerToTradeWith.name + ",");
                            makeTheTransaction(playerToTradeWith, cost, true);
                            anotherTurn = false;
                        }
                    }
                }
                case "2" -> {
                    if (!tradingPlayerHaveAnimals) {
                        System.out.println("Your opponent don't have any animals to sell!");
                        anotherTurn = true;
                    } else {
                        animalExist(playerToTradeWith);
                        if (player.money < (animal.price * (animal.health / 100) - animal.currentAge)) {
                            System.out.println("You don't have any money!");
                            anotherTurn = true;
                        } else {
                            System.out.println("The cost for " + animal.name + " is " + (animal.price * (animal.health / 100) - animal.currentAge) + " kr.");
                            player.animals.add(this.animal);
                            playerToTradeWith.animals.remove(this.animal);
                            double cost = (animal.price * (animal.health / 100) - animal.currentAge) > 0 ? (animal.price * (animal.health / 100) - animal.currentAge) : 0;
                            System.out.println(player.name + ",");
                            makeTheTransaction(player, cost, true);
                            System.out.println(playerToTradeWith.name + ",");
                            makeTheTransaction(playerToTradeWith, cost, false);
                            anotherTurn = false;
                        }
                    }
                }
                case "3" -> {return;}
                default -> throw new RuntimeException("Not a valid choice!");
                }
            } while(anotherTurn);
            activeRound = continueOrExit();
        } while (activeRound);
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
            System.out.println("Cow 100 kr | Fish 50 kr | Horse 120 kr | Rabbit 70 kr | Ostrich 150 kr\n");
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
        return choice;
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
        boolean continueRound = false;
        switch (choice){
            case "1" -> continueRound = true;
            case "2" -> continueRound = false;
        }
        return continueRound;
    }

    public boolean yesOrNo(){
        System.out.println("Yes[1] / No[2]?");
        String helpingOut = Prompt.inputCheck(input.nextLine().toLowerCase(), 1,2);
        return helpingOut.equals("1");
    }

}
