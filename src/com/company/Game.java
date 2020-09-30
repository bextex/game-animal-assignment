package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Random random = new Random();
    Scanner input = new Scanner(System.in);
    int round = random.nextInt(25) + 5;
    boolean firstRound = true;


    public Game(){
        System.out.println("Hello and welcome to the game PiggyBank!\n");
        sleep(1000);
        System.out.println("In this game you can play by yourself or with up to three friends.");
        System.out.println("To make it extra fun, we - the game masters - will choose between 5 and 30 rounds so the game stops when you least expect it... Good luck!");
        System.out.println("Press ENTER when you're ready to start.");
        input.nextLine();
        System.out.println("Choose how many players you want to be (1-4)");
        String playerNum = input.nextLine(); // -----Make som sort of control so there cannot be more than 4 or less than 1 player-----
        setPlayers(playerNum);
        sleep(1000);
        play();
    }

    public void setPlayers(String playerNum){
        int numOfPlayers = Integer.parseInt(playerNum);
        for(int i = 1; i <= numOfPlayers; i++){
            System.out.println("Type in the name of Player " + i + ":");
            String player = input.nextLine();
            Store.players.add(new Player(player));
        }
    }

    public void play(){ // The main playing field where all choices will be made and continue until the game is over
        if(firstRound){
            System.out.print("Welcome ");
            for(Player p : Store.players){
                System.out.print(p.name); // -----Fix formatting when welcoming the players!!!-----
            }
            firstRound = false;
        }
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
