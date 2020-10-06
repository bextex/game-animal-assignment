package com.company;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Random random = new Random();
    Scanner input = new Scanner(System.in);
    Store store = new Store();
    Player player;
    Animal animal, animal2, baby;
    Food food;
    String animalName, gender;
    boolean activeRound = true;
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
            sleep(500);
            System.out.println(round + " rounds left.");
            System.out.println();
            System.out.println(player.name + " is your turn!");
            playersHolding(player);
            System.out.println("------------------------------------");

            menu();
            String nextStep = input.nextLine();
            switch (nextStep) {
                case "1" -> store.buyAnimal(player);
                case "2" -> store.buyFood(player);
                case "3" -> feedAnimal(player);
                case "4" -> mateAnimal(player);
                case "5" -> store.sellAnimal(player);
                case "6" -> activeRound = false;
                default -> System.out.println("That's not an option.");
            }
            round--;
            if(round <= 0){
                System.out.println("------------------------------------");
                System.out.println("The game is over!");
                summarizeBelongings();
            }
        } while(round > 0);
    }

    public void setPlayers(String playerNum){
        int numOfPlayers = Integer.parseInt(playerNum);
        for(int i = 1; i <= numOfPlayers; i++){
            System.out.println("Type in the name of Player " + i + ":");
            String player = input.nextLine();
            Player.players.add(new Player(player));
        }
    }

    public void feedAnimal(Player player) throws Exception {
        do {
            this.animal = store.animalExist(player);
            this.animalName = store.animalName;
            String foodSelection = store.foodSelection();
            System.out.println(foodSelection);
            System.out.println("How many kilo of food do you wanna feed " + animalName + " with?");
            int kgOfFood = Integer.parseInt(input.nextLine());
            player.removeFood(animalName, foodSelection, kgOfFood);
            int newHealth = animal.health + (kgOfFood * 10);
            animal.health = Math.min(newHealth, 100);
            activeRound = store.continueOrExit();
        } while(activeRound);
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
        String animalClass = animal.getClass().getSimpleName().toLowerCase();
        int numOfBabies;
        switch (animalClass){
            case "cow", "horse", "ostrich" -> numOfBabies = 1;
            case "fish" -> numOfBabies = random.nextInt(10) + 5; // Minimize the numbers to fit the other animals
            case "rabbit" -> numOfBabies = random.nextInt(13) + 1;
            default -> throw new IllegalStateException("Unexpected value: " + animalClass);
        }
        if(matingOK){
            System.out.println("Congratulations, the mating was successful!");
            System.out.println(animal.name + " and " + animal2.name + " got " + numOfBabies + (numOfBabies >= 2 ? " babies." : " baby."));
            sleep(500);
            int countingBabies = 1;
            do {
                boolean gender = random.nextBoolean();
                String genderOfAnimal = gender ? "female" : "male";
                System.out.println("What do you want to name the " + genderOfAnimal + " " + (numOfBabies < 2 ? "baby?" : "baby nr " + countingBabies + "?"));
                String babyAnimalName = input.nextLine();
                String raceAnimal = this.animal.getClass().getSimpleName().toLowerCase();
                switch (raceAnimal) {
                    case "cow" -> baby = new Cow(babyAnimalName, genderOfAnimal);
                    case "fish" -> baby = new Fish(babyAnimalName, genderOfAnimal);
                    case "horse" -> baby = new Horse(babyAnimalName, genderOfAnimal);
                    case "rabbit" -> baby = new Rabbit(babyAnimalName, genderOfAnimal);
                    case "ostrich" -> baby = new Ostrich(babyAnimalName, genderOfAnimal);
                    default -> throw new Exception("Not successful!");
                }
                player.animals.add(baby);
                numOfBabies--;
                countingBabies++;
            } while(numOfBabies > 0);
        } else{
            System.out.println("No babies here, better luck next time!");
        }
    }

    public void playersHolding(Player player){
        System.out.println("These are your current holdings:");
        System.out.println("------------------------------------");
        System.out.println("Animals:" + (player.animals.size() == 0 ? " You don't own any animals." : ""));
        int[] negativeHealthValues = {10, 20, 30};
        for(Animal a : player.animals){
            int negativeHealthValue = random.nextInt(negativeHealthValues.length);
            a.health = a.health - negativeHealthValues[negativeHealthValue];
            System.out.println(a.getClass().getSimpleName() + " - " + a.name + " "
                    + (a.gender.equals("female") ? "(f)" : "(m)") + ". " + (a.health <= 0 ? "HAS DIED!!! x_x" : "Health: " + a.health));
        }
        player.animals.removeIf(a -> a.health <= 0);
        System.out.println();
        System.out.println("Foods:" + (player.foods.size() == 0 ? " You don't own any food." : ""));
        for(String key : player.foods.keySet()){
            System.out.println(key + " - " + player.foods.get(key) + " kg");
        }
    }
    public void menu(){
        System.out.println("1. Buy an animal.");
        System.out.println("2. Buy food.");
        System.out.println("3. Feed your animal(s).");
        System.out.println("4. Mate your animals.");
        System.out.println("5. Sell your animal(s).");
        System.out.println("6. Exit round.");
    }

    public void summarizeBelongings(){
        for(Player p : Player.players){
            for(Animal a : p.animals){
                p.money = p.money + (a.price * (a.health / 100));
            }
            System.out.println(p.name + " has a total of " + p.money + " kr.");
        }
    }

    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        }
        catch(Exception ignore){}
    }
}
