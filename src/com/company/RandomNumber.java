package com.company;

import java.util.Random;

public class RandomNumber {
    public static int randInt(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
