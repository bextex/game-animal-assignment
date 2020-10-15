package com.company;

import java.util.Scanner;

public class Prompt {

    static Scanner input = new Scanner(System.in);

    static public String inputCheck(String answer, int min, int max){
        int num = 0;
        try{
            num = Integer.parseInt(answer);
        } catch(Exception e){}

        return (num >= min && num <= max ? answer : runAgain(min, max));
    }

    static private String runAgain(int min, int max){
        System.out.println("Invalid number, try again!");
        return inputCheck(input.nextLine(), min, max);
    }

}
