package com.company.engine;

import com.company.ResourceBoundle.SingletonResourceBundle;
import com.company.TypeFileStrategy.StrategyFile;
import com.company.engine.GuessTheAnimal;

import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Menu {
    private final Scanner sc = new Scanner(System.in);
    private final ResourceBundle resourceBundle;

    public Menu() {
        resourceBundle = SingletonResourceBundle.getInstance();
    }

    private void greetingUser() {
        LocalTime now = LocalTime.now();
        LocalTime inTheMorning = LocalTime.of(5,0);
        LocalTime atNoon = LocalTime.of(12,0);
        LocalTime inTheEvening = LocalTime.of(18,0);
        if (now.isAfter(inTheMorning) && now.isBefore(atNoon)) {
            System.out.println(resourceBundle.getString("good morning"));
        } else if (now.isAfter(atNoon) && now.isBefore(inTheEvening)) {
            System.out.println(resourceBundle.getString("good afternoon"));
        } else {
            System.out.println(resourceBundle.getString("good evening"));
        }
    }

    public void start(StrategyFile strategyFile) {
        greetingUser();
        boolean startMenu = true;
        GuessTheAnimal guessTheAnimal = new GuessTheAnimal(strategyFile);
        String firstAnimal = guessTheAnimal.start();
        while (startMenu) {
            printMenu();
            int numberOfChoice = Integer.parseInt(sc.nextLine());
            switch (numberOfChoice) {
                case 1:
                    guessTheAnimal.guessingGame(firstAnimal);
                    break;
                case 2:
                    guessTheAnimal.listOfAllAnimals();
                    break;
                case 3:
                    guessTheAnimal.factAboutAnimal();
                    break;
                case 4:
                    guessTheAnimal.calculateStatistic();
                    break;
                case 0:
                    startMenu = false;
                    System.out.println(resourceBundle.getString("thank you"));
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println(resourceBundle.getString("menu"));
    }
}
