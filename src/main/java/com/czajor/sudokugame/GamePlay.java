package com.czajor.sudokugame;

import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.Character.getNumericValue;

public class GamePlay {
    private static final String FIELD_PARAMETERS_PATTERN = "\\d,\\d,\\d";

    public boolean solveSudoku() {
        printIntro();
        SudokuBoard board = new SudokuBoard(getNumericValue(getDigitUserInput().charAt(0)));

        String input;
        switch(input = getStringUserInput()) {
            case "SUDOKU":
                solveSudoku();
                System.out.println("solving sudoku.....");
                break;
            default:
                if(inputMatchesFieldParameters(input)) {
                    board.setFieldValue(getNumericValue(input.charAt(0)), getNumericValue(input.charAt(2)), getNumericValue(input.charAt(4)));
                } else {
                    System.out.println("Wrong input!");
                }
                break;
        }

        System.out.println(board);
        return true;
    }

    public String getStringUserInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public String getDigitUserInput() {
        Scanner input = new Scanner(System.in);
        return input.next("\\d");
    }

    public boolean inputMatchesFieldParameters(String input) {
        Pattern pattern = Pattern.compile(FIELD_PARAMETERS_PATTERN);
        return pattern.matcher(input).matches();
    }

    public void printIntro() {
        System.out.println("Welcome to Sudoku game!\n"
                + "Please provide board size:\n");
    }
}
