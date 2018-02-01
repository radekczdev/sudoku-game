package com.czajor.sudokugame.tools;

import java.util.Scanner;

public class InputProcessor {
    public static String getStringUserInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static String getDigitUserInput() {
        Scanner input = new Scanner(System.in);
        return input.next("\\d");
    }
}
