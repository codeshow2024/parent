package top.codeshow.common.util;


import java.util.Random;

public class MyNumberUtils {
    private static final Random random = new Random();

    public static int getRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static int getRandomNumber() {
        return getRandomNumber(-999999, 999999);
    }
}
