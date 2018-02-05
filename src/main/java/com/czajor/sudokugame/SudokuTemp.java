package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;

public class SudokuTemp {
    SudokuBoard deepCopy = new SudokuBoard();
    int fieldRow;
    int fieldColumn;
    int fieldValue;

    public SudokuTemp(SudokuBoard board, int fieldRow, int fieldColumn, int fieldValue) {
        this.deepCopy = board;
        this.fieldRow = fieldRow;
        this.fieldColumn = fieldColumn;
        this.fieldValue = fieldValue;
    }

    public SudokuBoard getDeepCopy() {
        return deepCopy;
    }

    public int getFieldRow() {
        return fieldRow;
    }

    public int getFieldColumn() {
        return fieldColumn;
    }

    public int getFieldValue() {
        return fieldValue;
    }
}
