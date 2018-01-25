package com.czajor.sudokugame;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Character.getNumericValue;

public class GamePlay {
    private static final String FIELD_PARAMETERS_PATTERN = "\\d,\\d,\\d";
    SudokuBoard board;

    public boolean startGame() {
        printIntro();
        board = new SudokuBoard(getNumericValue(getDigitUserInput().charAt(0)));
        System.out.println("Please provide field data (column,row,value) or solve by SUDOKU: ");
        while(!getUserInput()){

        }
        return true;
    }

    public boolean getUserInput() {
        String input;
        switch(input = getStringUserInput()) {
            case "SUDOKU":
                SudokuSolver solver = new SudokuSolver(board);
                solver.solveSudoku();
                System.out.println(solver.getBoard());
                return true;
            default:
                if(inputMatchesFieldParameters(input)) {
                    setFieldValue(input);
                    System.out.println(board);
                } else {
                    System.out.println("Wrong input!");
                }
                break;
        }
        return false;
    }

    public boolean setFieldValue(String input) {
        return board.setField(getNumericValue(input.charAt(0)), getNumericValue(input.charAt(2)), getNumericValue(input.charAt(4)));
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
