package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;

public class SudokuTemp {
    private final SudokuBoard deepCopy;
    private final int guessedValue;

    public SudokuTemp(SudokuBoard board, int fieldValue) {
        this.deepCopy = board;
        this.guessedValue = fieldValue;
    }

    public SudokuBoard getDeepCopy() {
        return deepCopy;
    }

    public int getGuessedValue() {
        return guessedValue;
    }
}
