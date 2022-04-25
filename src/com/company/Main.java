package com.company;

import com.beust.jcommander.JCommander;
import com.company.TypeFileStrategy.ChooseFileStrategyType;
import com.company.TypeFileStrategy.StrategyFile;
import com.company.engine.GuessTheAnimalArgs;
import com.company.engine.Menu;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale locale = Locale.ENGLISH;
        GuessTheAnimalArgs guessTheAnimalArgs = new GuessTheAnimalArgs();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(guessTheAnimalArgs)
                .build();
        jCommander.parse(args);
        StrategyFile strategyFile = ChooseFileStrategyType.
                choseStrategy(guessTheAnimalArgs, locale);
        Menu menu = new Menu();
        menu.start(strategyFile);
    }
}
