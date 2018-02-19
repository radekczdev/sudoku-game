package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;

public class SudokuTemp {
    private final SudokuBoard boardCopy;
    private final int row;
    private final int column;
    private final int value;

    public SudokuTemp(SudokuBoard boardCopy, SudokuField field) {
        this.boardCopy = boardCopy;
        this.row = field.getRow();
        this.column = field.getColumn();
        this.value = field.getNextPossibleValue();
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
