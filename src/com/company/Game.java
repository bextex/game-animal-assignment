package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Random random = new Random();
    Scanner input = new Scanner(System.in);
    int round = random.nextInt(25) + 5;
    boolean firstRound = true;
    Player currentPlayer = Player.players.get(0);


    public Game() throws Exception {
        System.out.println("Hello and welcome to the game PiggyBank!\n");
        sleep(500);
        System.out.println("In this game you can play by yourself or with up to three friends.");
        System.out.println("To make it extra fun, we - the game masters - will choose between 5 and 30 rounds so the game stops when you least expect it... Good luck!");
        System.out.println("Press ENTER when you're ready to start.");
        input.nextLine();
        System.out.println("Choose how many players you want to be (1-4)");
        String playerNum = input.nextLine(); // -----Make som sort of control so there cannot be more than 4 or less than 1 player-----
        setPlayers(playerNum);
        sleep(500);
        play();
    }

    public void setPlayers(String playerNum){
        int numOfPlayers = Integer.parseInt(playerNum);
        for(int i = 1; i <= numOfPlayers; i++){
            System.out.println("Type in the name of Player " + i + ":");
            String player = input.nextLine();
            Player.players.add(new Player(player));
        }
    }

    public void play() throws Exception { // The main playing field where all choices will be made and continue until the game is over
        currentPlayer = Player.players.get((Player.players.indexOf(currentPlayer) + 1 ) % Player.players.size());


        if(firstRound){
            System.out.print("Welcome ");
            for(Player p : Player.players){
                System.out.println(p.name); // -----Fix formatting when welcoming the players!!!-----
            }
            firstRound = false;
            round--;
        }
        playersHolding(currentPlayer); // Presents the current players holdings
        System.out.println("What is your next step?");
        menu();
        String nextStep = input.nextLine();
        switch (nextStep){
            case "1" -> buyAnimal(currentPlayer);
            case "2" -> buyFood(currentPlayer);
            case "3" -> feedAnimal(currentPlayer);
            case "4" -> mateAnimal(currentPlayer);
            case "5" -> sellAnimal(currentPlayer);
            default -> System.out.println("That's not an option.");
        }
    }

    public void buyAnimal(Player player) throws Exception{
        System.out.println("What animal would you like to buy? Cow, fish, horse, rabbit or ostrich?");
        String choice = input.nextLine().toLowerCase();
        System.out.println("Type the name of your animal.");
        String animalName = input.nextLine();
        System.out.println("Type in 'female' or 'male' for gender of your animal.");
        String animalGender = input.nextLine();
        if(choice.contains("cow")){
            player.animals.add(new Cow(animalName, animalGender));
        } else if(choice.contains("fish")){
            player.animals.add(new Fish(animalName, animalGender));
        } else if(choice.contains("horse")){
            player.animals.add(new Horse(animalName, animalGender));
        } else if(choice.contains("rabbit")){
            player.animals.add(new Rabbit(animalName, animalGender));
        } else if(choice.contains("ostrich")){
            player.animals.add(new Ostrich(animalName, animalGender));
        } else{
            throw new Exception("Not valid.");
        }
    }

    public void buyFood(Player player) throws Exception {
        System.out.println("What food would you like to buy? Grass, corn or meat?");
        String choice = input.nextLine().toLowerCase();
        System.out.println("How much " + choice + " would you like to buy?");
        if(choice.contains("grass")){
            // add "grass" as food if not added, change kg of food the player have
        } else if(choice.contains("corn")){
            // add "grass" as food if not added, change kg of food the player have
        } else if(choice.contains("meat")){
            // add "grass" as food if not added, change kg of food the player have
        } else{
            throw new Exception("Not valid.");
        }
    }

    public void feedAnimal(Player player){

    }

    public void mateAnimal(Player player){

    }

    public void sellAnimal(Player player){

    }

    public void playersHolding(Player player){
        System.out.println(player.name + ", you currently have " + player.money + "kr and these are your holdings:");
        if(player.animals.size() == 0){
            System.out.println("You don't own any animals for now.");
        } else{
            for(Animal a : player.animals){
                System.out.println("The " + a.getClass().getSimpleName() + " " + a.name + " with health " + a.health);
            }
        }
        if(player.foods.size() == 0){
            System.out.println("You don't own any food for now.");
        } else {
            for(Food f : player.foods){ // kilo of each food???

                System.out.println(f.kgFood + "kg of " + f.name);
            }
        }
    }

    public void menu(){
        System.out.println("1. Buy an animal.");
        System.out.println("2. Buy food.");
        System.out.println("3. Feed your animal(s).");
        System.out.println("4. Mate your animals.");
        System.out.println("5. Sell your animal(s).");
    }

    public void mating(Animal animal1, Animal animal2) throws Exception {
        if(animal1.getClass().getSimpleName().equals(animal2.getClass().getSimpleName())){
            if(!animal1.gender.equals(animal2.gender)) {
                System.out.print("The mating has begun... ");
                sleep(2000);
                boolean matingOK = random.nextBoolean();
                boolean genderReveal = random.nextBoolean();
                String gender = (genderReveal ? "female" : "male");
                if(matingOK){
                    System.out.println("The mating was successful!");
                    System.out.println(animal1.name + " and " + animal2.name + " got a " + gender + " baby.");
                    sleep(1000);
                    System.out.println("Name the new baby:");
                    switch (animal1.getClass().getSimpleName()){
                        case "Cow" -> new Cow(input.nextLine(), gender);
                        case "Fish" -> new Fish(input.nextLine(), gender);
                        case "Horse" -> new Horse(input.nextLine(), gender);
                        case "Rabbit" -> new Rabbit(input.nextLine(), gender);
                        case "Ostrich" -> new Ostrich(input.nextLine(), gender);
                        default -> throw new Exception("Something went wrong");
                    }
                } else{
                    System.out.println("No babies here, better luck next time.");
                }
            } else {
                System.out.println("Unfortunately, same sex animals cannot mate.");
            }
        } else {
            System.out.println("You cannot mate animals from different races.");
        }
    }

    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        }
        catch(Exception ignore){}
    }



}
