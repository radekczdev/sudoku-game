package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;

public class SudokuTemp {
    private final SudokuBoard boardCopy;
    private final int row;
    private final int column;
    private final int value;

    public SudokuTemp(SudokuBoard boardCopy, SudokuField fieldCopy) {
        this.boardCopy = boardCopy;
        this.row = fieldCopy.getRow();
        this.column = fieldCopy.getColumn();
        this.value = fieldCopy.getNextPossibleValue();
    }

    public SudokuBoard getBoardCopy() {
        return boardCopy;
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
