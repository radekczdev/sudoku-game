package com.czajor.sudokugame.sections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuBoard {
    private final List<SudokuRow> rowsArray = new ArrayList<>(9);
    private final static int boardSize = 9;
    private final static int blockSize = 3;

    public SudokuBoard() {
        IntStream.iterate(0, n -> n + 1).limit(boardSize).forEach(n -> rowsArray.add(n, new SudokuRow(boardSize)));
    }

    public int getBoardSize() {
        return boardSize;
    }

    public List<SudokuRow> getRowsArray() {
        return rowsArray;
    }


    public SudokuField getField(int row, int column) {
        return rowsArray.get(row).getField(column);
    }

    public int getFieldValue(int row, int column) {
        return rowsArray.get(row).getFieldValue(column);
    }

    public boolean setFieldValue(int row, int column, int value) {
        if(isSingleInRowColumnBlock(row,column,value)) {
            System.out.println("Cannot set field " + row + "," + column + " to " + value + " - it exists in this row or column or block");
            return false;
        }
        return rowsArray.get(row).getField(column).setValue(value);
    }

    public void removeWrittenValuesFromFieldsInColumns(Set<Integer> writtenValues, int columnNumber) {
    	for(int i = 0; i < boardSize; i++) {
            getField(i, columnNumber).getPossibleValues().removeAll(writtenValues);
        }
    }

    public boolean isSingleInRowColumnBlock(int row, int column, int value) {
        return getValuesFromColumn(column).contains(value) || getValuesFromRow(row).contains(value) || getValuesFromBlock(row, column).contains(value);
    }

    public Set<Integer> getValuesFromRow(int row) {
        return getRowsArray().get(row).getFieldsArray().stream()
                .map(SudokuField::getValue)
                .filter(n -> n > 0)
                .sorted()
                .collect(Collectors.toSet());
    }

    public Set<Integer> getValuesFromColumn(int column) {
        Set<Integer> values = new HashSet<>();
        for(int i = 0; i < boardSize; i++) {
            values.add(getFieldValue(i, column));
        }
        return values;
    }

    public Set<Integer> getValuesFromBlock(int row, int column) {
        int rowMod = (row / blockSize) * blockSize;
        int columnMod = (column / blockSize) * blockSize;

        Set<Integer> values = new HashSet<>();
        for(int i = rowMod; i < rowMod + blockSize; i++) {
            for (int j = columnMod; j < columnMod + blockSize; j++) {
                values.add(getFieldValue(i, j));
            }
        }
        return values;
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
                board += "+ - ";
            }
            board += "\n";
        }
        return board;
    }
}