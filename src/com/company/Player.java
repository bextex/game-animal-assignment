package com.company;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;

public class Player {
    Scanner input = new Scanner(System.in);
    Random random = new Random();
    public static ArrayList<Player> players = new ArrayList<>();
    public ArrayList<Animal> animals = new ArrayList<>();
    LinkedHashMap<String, Integer> foods = new LinkedHashMap<>();
    Store store = new Store();
    Food food;
    Animal animal, animal2;
    boolean activeRound;
    public String name, choiceAsString, animalName, causeOfDeath;
    public int money = 1000;

    public Player(String name) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public Food convertStringToFood(String choice) {
        switch (choice) {
            case "1" -> this.food = new Grass();
            case "2" -> this.food = new Corn();
            case "3" -> this.food = new Meat();
            default -> throw new RuntimeException("Not a valid choice");
        }
        return this.food;
    }

    public void addFood(String choice, int foodInKg) {
        switch (choice){
            case "1" -> choiceAsString = "Grass";
            case "2" -> choiceAsString = "Corn";
            case "3" -> choiceAsString = "Meat";
        }
        if (!foods.containsKey(choiceAsString)) {
            foods.put(choiceAsString, foodInKg);
        } else {
            int value = foods.get(choiceAsString);
            int newValue = value + foodInKg;
            foods.put(choiceAsString, newValue);
        }
    }

    private boolean animalEatFood(Food food, String animalName) {
        for (Animal a : animals) {
            if (a.name.toLowerCase().equals(animalName.toLowerCase())) {
                return (a.eatGrain && food.grain || a.eatMeat && food.meat || a.eatVegetable && food.vegetable);
            }
        }
        return false;
    }

    public boolean removeFood(String animalName, String choice, int foodInKg) {
        switch (choice){
            case "1" -> choiceAsString = "Grass";
            case "2" -> choiceAsString = "Corn";
            case "3" -> choiceAsString = "Meat";
        }
        boolean okFoodChoice = animalEatFood(convertStringToFood(choice), animalName);
        if(okFoodChoice) {
            if (foods.containsKey(choiceAsString)) {
                if (foodInKg > foods.get(choiceAsString)) {
                    System.out.println("You don't have that much food.\n");
                    return false;
                } else {
                    int value = foods.get(choiceAsString);
                    value = value - foodInKg;
                    foods.put(choiceAsString, value);
                    System.out.println("Yummi! That tasted good!\n");
                    return true;
                }
            } else {
                System.out.println("You don't own any " + choiceAsString.toLowerCase() + ".");
            }
        } else {
            System.out.println("URK! Stop feeding me with this shit.\n");
        }
        return false;
    }

    public void cleanList(){
        ArrayList<String> keysToDelete = new ArrayList<>();
        int value;
        for(String key : foods.keySet()){
            value = foods.get(key);
            if(value == 0){
                keysToDelete.add(key);
            }
        }
        for(String key : keysToDelete){
            foods.remove(key);
        }
    }

    public void feedAnimal(Player player){
        do {
            this.animal = store.animalExist(this);
            if(animal == null){
                System.out.println("This animal doesn't exist.");
                activeRound = store.continueOrExit();
                if(activeRound){
                    feedAnimal(this);
                } else {
                    break;
                }
            }
            this.animalName = store.animalName;
            String foodSelection = Prompt.inputCheck(store.foodSelection(), 1, 3);
            System.out.println(animal.name + " has " + (int)animal.health + " in health and only need " + (int)((100 - animal.health) / 10) + " kg to have full strength!");
            System.out.println("How many kilo of food do you wanna feed " + animal.name + " with?");
            int kgOfFood = Integer.parseInt(Prompt.inputCheck(input.nextLine(), 1, 200));
            if(player.removeFood(animalName, foodSelection, kgOfFood)){
                double newHealth = animal.health + (kgOfFood * 10);
                animal.health = Math.min(newHealth, 100);
            }
            player.cleanList();
            activeRound = store.continueOrExit();
        } while(activeRound);
    }

    public void mateAnimal(Player player) {
        System.out.println("So you want to try mate your animals. Not everyone succeeds...but good luck!");
        this.animal = store.animalExist(player);
        this.animal2 = store.animalExist(player);
        if(!animal.getClass().equals(animal2.getClass())){
            System.out.println("Animals with different races cannot mate...");
            return;
        } else if(animal.getGender().equals(animal2.getGender())){
            System.out.println("Unfortunately, same sex animals cannot have babies.");
            return;
        }
        System.out.print("Let's see... The mating has begun...");
        Game.sleep(2000);
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
            System.out.println(animal.name + " and " + animal2.name + " got " + numOfBabies + (numOfBabies >= 2 ? " babies." : " baby.")+ "\n");
            Game.sleep(500);
            int countingBabies = 1;
            do {
                System.out.println("----- Baby" + (" nr " + countingBabies) + " -----");
                store.animalSelection(player, true);
                numOfBabies--;
                countingBabies++;
            } while(numOfBabies > 0);
        } else{
            System.out.println("No babies here, better luck next time!");
        }
    }

    public void healthDecreasing(Player player){
        int[] negativeHealthValues = {10, 20, 30};
        for(Animal a : player.animals){
            int negativeHealthValue = random.nextInt(negativeHealthValues.length);
            a.health = a.health - negativeHealthValues[negativeHealthValue];
            if(a.health <= 0){
                setCauseOfDeath("NEGLECTED CARE");
            }
        }
    }

    public void sickness(Player player){
        int[] chanceOfSicknessValues = new int[5];
        for(Animal a : player.animals){
            int chanceOfSicknessValue = random.nextInt(chanceOfSicknessValues.length);
            if(chanceOfSicknessValue == 0){
                System.out.println(a.name + " has gotten sick! Do you wanna pay for veterinary cost " + getVeterinaryCost(a) + " kr,"
                        + " to try to save " + (a.gender.equals("female") ? "her" : "him") + "?");
                boolean wantsToHelp = store.yesOrNo();
                if(wantsToHelp){
                    double payForVeterinary = veterinaryCost(a);
                    boolean moneyForVeterinary = store.makeTheTransaction(player, payForVeterinary, true);
                    if(moneyForVeterinary){
                        boolean saveLife = veterinary();
                        if(!saveLife){
                            a.health = 0;
                            setCauseOfDeath("SICKNESS");
                        }
                    } else {
                        a.health = 0;
                        setCauseOfDeath("SICKNESS");
                    }
                } else {
                    a.health = 0;
                    setCauseOfDeath("SICKNESS");
                }
            }
        }
    }

    public void animalAging(Player player) {
        for (Animal a : player.animals) {
            a.currentAge = a.currentAge + (a.maxAge < 10 ? 1 : (a.maxAge / 10));
            if(a.currentAge >= a.maxAge){
                setCauseOfDeath("OLD AGE");
            }
        }
    }

    public boolean veterinary(){
        boolean gettingBetter = random.nextBoolean();
        System.out.println(gettingBetter ? "...And, you manage to save your animal!\n" : "...But, the treatment didn't help. Your animal passed away\n");
        return gettingBetter;
    }

    public double getVeterinaryCost(Animal animal){
        return veterinaryCost(animal);
    }

    public double veterinaryCost(Animal animal){
        return animal.price * 1.5;
    }

    private void setCauseOfDeath(String death){
        this.causeOfDeath = death;
    }
}

