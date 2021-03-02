package me.travis.javaga;

import java.util.Random;

public class Util {

    private static final Random random = new Random();

    public static int oneOrZero() {
        return random.nextInt(2);
    }

}
