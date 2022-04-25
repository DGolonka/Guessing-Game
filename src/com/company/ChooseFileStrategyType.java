package com.company;

import java.util.Locale;

public class ChooseFileStrategyType {
    public static StrategyFile choseStrategy(GuessTheAnimalArgs guessTheAnimalArgs,
        Locale locale) {
        String typeFile = guessTheAnimalArgs.getTypeFile();
        if (typeFile.equals("json")) {
            return new StrategyJson(locale);
        } else if (typeFile.equals("xml")) {
            return  new StrategyXml(locale);
        } else if (typeFile.equals("yaml")) {
            return  new StrategyYaml(locale);
        } else {
            return null;
        }
    }
}
