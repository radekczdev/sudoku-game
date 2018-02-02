package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.tools.InputProcessor;

import java.util.regex.Pattern;

import static java.lang.Character.getNumericValue;

public class GamePlay {
    private static final String FIELD_PARAMETERS_PATTERN = "\\d\\d\\d";
    private final static int RELATIVE_POS = 1;
    private SudokuBoard board;

    public boolean startGame() {
        printIntro();
        board = new SudokuBoard();
        while(!userAction()){

        }
        return true;
    }

    public boolean userAction() {
        String input;
        switch(input = InputProcessor.getStringUserInput()) {
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
        return board.setFieldValue(getNumericValue(input.charAt(0)) - RELATIVE_POS,
                getNumericValue(input.charAt(1)) - RELATIVE_POS,
                getNumericValue(input.charAt(2)));
    }

    public boolean inputMatchesFieldParameters(String input) {
        Pattern pattern = Pattern.compile(FIELD_PARAMETERS_PATTERN);
        return pattern.matcher(input).matches();
    }

    public void printIntro() {
        System.out.println("Welcome to Sudoku game!\n");
        System.out.println("Please provide field data (rowColumnValue) or solve by SUDOKU: ");
    }
}
