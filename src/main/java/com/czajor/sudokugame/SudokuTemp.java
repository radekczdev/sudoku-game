package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;

public class SudokuTemp {
    private final SudokuBoard boardCopy;
    private final SudokuField fieldCopy;
    private final SudokuField address;

    public SudokuTemp(SudokuBoard boardCopy, SudokuField fieldCopy, SudokuField address) {
        this.boardCopy = boardCopy;
        this.fieldCopy = fieldCopy;
        this.address = address;
    }

    public SudokuBoard getBoardCopy() {
        return boardCopy;
    }

    public SudokuField getFieldCopy() {
        return fieldCopy;
    }

    public SudokuField getAddress() {
        return address;
    }
}
