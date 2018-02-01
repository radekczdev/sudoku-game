package com.czajor.sudokugame.sections;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SudokuBoard {
    private final List<SudokuRow> rowsArray = new ArrayList<>(9);
    private final int boardSize;

    public SudokuBoard(int boardSize) {
        this.boardSize = boardSize;
        IntStream.iterate(0, n -> n + 1).limit(boardSize).forEach(n -> rowsArray.add(n, new SudokuRow(boardSize)));
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getFieldValue(int row, int column) {
        return rowsArray.get(row).getFieldValue(column);
    }

    public SudokuField getField(int row, int column) {
        return rowsArray.get(row).getField(column);
    }

    public boolean setFieldValue(int row, int column, int value) {
        return rowsArray.get(row).getField(column).setValue(value);
    }

    public List<SudokuRow> getRowsArray() {
        return rowsArray;
    }

    @Override
    public String toString() {
        String board = "";
        // add division into Blocks!!!
        for(SudokuRow row : rowsArray) {
            board += "|";
            for(SudokuField field : row.getFieldsArray()) {
                board += (field.getValue() == SudokuField.EMPTY) ? "   |" : " " + field.getValue() + " |";
            }
            board += "\n";
            for(int i = 1; i <= row.getFieldsArray().size(); i++) {
                board += "  - ";
            }
            board += "\n";
        }
        return board;
    }
}
