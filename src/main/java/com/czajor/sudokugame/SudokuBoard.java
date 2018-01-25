package com.czajor.sudokugame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SudokuBoard {
    private final static int RELATIVE_POS = 1;
    private final List<SudokuRow> rowsArray = new ArrayList<>(9);

    public SudokuBoard(int boardSize) {
        IntStream.iterate(0, n -> n + 1).limit(boardSize).forEach(n -> rowsArray.add(n, new SudokuRow(boardSize)));
    }

    public int getFieldValue(int row, int column) {
        return rowsArray.get(column - RELATIVE_POS).getFieldValue(row - RELATIVE_POS);
    }

    public boolean setField(int row, int column, int value) {
        return rowsArray.get(row - RELATIVE_POS).getField(column - RELATIVE_POS).setValue(value);
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
