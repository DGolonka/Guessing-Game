package com.company.engine;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TypeAnswer {
    public static boolean isItATrueAnswer(ResourceBundle resourceBundle) {
        String[]  answers = resourceBundle.getStringArray("answer");
        String[] positive = resourceBundle.getStringArray("positive");
        String[] negative = resourceBundle.getStringArray("negative");
        final Scanner scanner = new Scanner(System.in);
        String answer = "";
        while (true) {
            answer = scanner.nextLine().trim();
            for (String v : positive) {
                String regex = "^" + v.toLowerCase(Locale.ROOT) + "[.!]?$";
                if (answer.toLowerCase(Locale.ROOT).matches(regex)) {
                    return true;
                }
            }
            for (String v : negative) {
                String regex = "^" + v.toLowerCase(Locale.ROOT) + "[.!]?$";
                if (answer.toLowerCase(Locale.ROOT).matches(regex)) {
                    return false;
                }
            }
            System.out.println(resourceBundle.getString("contains yes no"));
        }
    }
}
