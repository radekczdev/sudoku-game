package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;

public class SudokuTemp {
    private final SudokuBoard boardCopy;
    private final SudokuField fieldCopy;

    public SudokuTemp(SudokuBoard boardCopy, SudokuField fieldCopy) {
        this.boardCopy = boardCopy;
        this.fieldCopy = fieldCopy;
    }

    public SudokuBoard getBoardCopy() {
        return boardCopy;
    }

    public SudokuField getFieldCopy() {
        return fieldCopy;
    }
}
