package com.company;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Random random = new Random();
    Scanner input = new Scanner(System.in);
    Store store = new Store();
    Player player;
    Animal animal, animal2, baby;
    Food food;
    String animalName;
    boolean gameOver = true;
    String[] validNumbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    int round = random.nextInt(25) + 5;
    boolean firstRound = true;
    //Player currentPlayer = Player.players.get(0);

    public Game() throws Exception {
        System.out.println("Welcome to the Game - PiggyBank!");
        System.out.println("Type in number of players (1-4)");
        String choice = input.nextLine();
        setPlayers(choice);
        this.player = Player.players.get(0);
        do {
            System.out.println(player.name + " is your turn!");
            menu();
            String nextStep = input.nextLine();
            switch (nextStep) {
                case "1" -> store.buyAnimal(player);
                case "2" -> store.buyFood(player);
                case "3" -> feedAnimal(player);
                case "4" -> mateAnimal(player);
                case "5" -> store.sellAnimal(player);
                default -> System.out.println("That's not an option.");
            }
        } while(gameOver);
    }

    public void setPlayers(String playerNum){
        int numOfPlayers = Integer.parseInt(playerNum);
        for(int i = 1; i <= numOfPlayers; i++){
            System.out.println("Type in the name of Player " + i + ":");
            String player = input.nextLine();
            Player.players.add(new Player(player));
        }
    }


/*
    public Game(Player player) throws Exception {
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



    public void play() throws Exception { // The main playing field where all choices will be made and continue until the game is over
        //currentPlayer = Player.players.get((Player.players.indexOf(currentPlayer) + 1 ) % Player.players.size());


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

    }*/




    public void feedAnimal(Player player){
        while(true) {
            System.out.println("Type in the name of the animal you would like to feed:");
            this.animalName = input.nextLine();
            for (int i = 0; i < player.animals.size(); i++) {
                if (!animalName.equals(player.animals.get(i).name)) {
                    System.out.println("You don't own a animal called " + animalName);
                } else {
                    this.animal = player.animals.get(i);
                }
            }
            System.out.println("Choose the food you want to feed " + animalName + " with.");
            System.out.println("Grass[1], corn[2] or meat[3]? Or Exit[4] if you want to return to menu.");
            String choice = input.nextLine().toLowerCase();
            switch (choice) {
                case "1", "grass" -> this.food = new Grass();
                case "2", "corn" -> this.food = new Corn();
                case "3", "meat" -> this.food = new Meat();
                case "4", "exit" -> {
                    break;
                }
                default -> System.out.println("Not valid.");
            }
            System.out.println("Type in how many kilos food you want to feed " + animalName + " with.");
            System.out.println(animalName + " currently have " + (this.animal.health == 100 ? " full health" : (this.animal.health + " and you can feed " + this.animal.gender
                    + " with max " + (100 - this.animal.health) + " kilos.")));
            int foodInKg = input.nextInt();
            player.removeFood(food, foodInKg);
            System.out.println("Feed more animals or return to menu?");
        }
    }


    public void mateAnimal(Player player) throws Exception {
        System.out.println("So you want to try mate your animals. Not everyone succeeds...but good luck!");
        System.out.println("Type in the name of your first animal:");
        String animalName1 = input.nextLine().toLowerCase();
        System.out.println("Type in the name of the second animal:");
        String animalName2 = input.nextLine().toLowerCase();
        if(player.animals.size() < 2) {
            System.out.println("You don't have enough animals to mate. You should buy more!");
            return;
        }
        for(Animal a : player.animals){
            if(animalName1.equals(a.name.toLowerCase())){
                this.animal = a;
            }
            for(Animal b : player.animals){
                if(animalName2.equals(b.name.toLowerCase())){
                    this.animal2 = b;
                }
            }
        }
        if(!animal.getClass().equals(animal2.getClass())){
            System.out.println("Animals with different races cannot mate...");
            return;
        } else if(animal.getGender().equals(animal2.getGender())){
            System.out.println("Unfortunately, same sex animals cannot have babies.");
            return;
        }
        System.out.print("Let's see... The mating has begun...");
        sleep(2000);
        boolean matingOK = random.nextBoolean();
        boolean gender = random.nextBoolean();
        if(matingOK){
            String genderOfAnimal = gender ? "female" : "male";
            System.out.println("Congratulations, the mating was successful!");
            System.out.println(animal.name + " and " + animal2.name + " got a " + genderOfAnimal + " baby.");
            sleep(500);
            System.out.println("What do you want to name the baby?");
            String babyAnimalName = input.nextLine();
            String raceAnimal = this.animal.getClass().getSimpleName().toLowerCase();
            switch (raceAnimal){
                case "cow" -> baby = new Cow(babyAnimalName, genderOfAnimal);
                case "fish" -> baby = new Fish(babyAnimalName, genderOfAnimal);
                case "horse" -> baby = new Horse(babyAnimalName, genderOfAnimal);
                case "rabbit" -> baby = new Rabbit(babyAnimalName, genderOfAnimal);
                case "ostrich" -> baby = new Ostrich(babyAnimalName, genderOfAnimal);
                default -> throw new Exception("Not successful!");
            }
            player.animals.add(baby);
        } else{
            System.out.println("No babies here, better luck next time!");
        }
    }


/*
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
*/
    public void menu(){
        System.out.println("1. Buy an animal.");
        System.out.println("2. Buy food.");
        System.out.println("3. Feed your animal(s).");
        System.out.println("4. Mate your animals.");
        System.out.println("5. Sell your animal(s).");
    }
/*
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
*/
    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        }
        catch(Exception ignore){}
    }



}
