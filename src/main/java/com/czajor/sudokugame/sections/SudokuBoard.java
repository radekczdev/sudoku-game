package com.czajor.sudokugame.sections;

import com.czajor.sudokugame.Prototype;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuBoard extends Prototype {
    private List<SudokuRow> rowsArray = new ArrayList<>(boardSize);
    private final static int boardSize = 9;
    private final static int blockSize = 3;

    public SudokuBoard() {
        IntStream.iterate(0, n -> n + 1).limit(boardSize).forEach(n -> rowsArray.add(n, new SudokuRow(boardSize)));
        for(int i = 0; i < rowsArray.size(); i++) {
            for(int j = 0; j < boardSize; j++) {
                rowsArray.get(i).getFieldsArray().get(j).setRow(i);
            }
        }
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

    public boolean setFieldValue(int row, int column, int value) {
        if(isSingleInRowColumnBlock(row,column,value)) {
            System.out.println("You cannot set the value: " + value + " in field [" + row + "," + column + "]");
            return false;
        }
        return rowsArray.get(row).getField(column).setValue(value);
    }

    private boolean isSingleInRowColumnBlock(int row, int column, int value) {
        return getValuesFromColumn(column).contains(value) || getValuesFromRow(row).contains(value) || getValuesFromBlock(row, column).contains(value);
    }

    public Set<Integer> getValuesFromRow(int row) {
        return getRowsArray().get(row).getFieldsArray().stream()
                .map(SudokuField::getValue)
                .filter(n -> n > 0)
                .sorted()
                .collect(Collectors.toSet());
    }

    public Set<Integer> getPossibleValuesFromRow(int row) {
        return getRowsArray().get(row).getFieldsArray().stream()
                .flatMap(n -> n.getPossibleValues().stream())
                .collect(Collectors.toSet());
    }

    public Set<Integer> getValuesFromColumn(int column) {
        Set<Integer> values = new HashSet<>();
        for(int i = 0; i < boardSize; i++) {
            values.add(getField(i, column).getValue());
        }
        return values;
    }

    public Set<Integer> getPossibleValuesFromColumn(int column) {
        Set<Integer> values = new HashSet<>();
        for(int i = 0; i < boardSize; i++) {
            values.addAll(getField(i, column).getPossibleValues());
        }
        return values;
    }

    public Set<Integer> getPossibleValuesFromBlock(int row, int column) {
        int rowMod = (row / blockSize) * blockSize;
        int columnMod = (column / blockSize) * blockSize;

        Set<Integer> values = new HashSet<>();
        for(int i = rowMod; i < rowMod + blockSize; i++) {
            for (int j = columnMod; j < columnMod + blockSize; j++) {
                values.addAll(getField(i, j).getPossibleValues());
            }
        }
        return values;
    }

    public Set<Integer> getValuesFromBlock(int row, int column) {
        int rowMod = (row / blockSize) * blockSize;
        int columnMod = (column / blockSize) * blockSize;

        Set<Integer> values = new HashSet<>();
        for(int i = rowMod; i < rowMod + blockSize; i++) {
            for (int j = columnMod; j < columnMod + blockSize; j++) {
                values.add(getField(i, j).getValue());
            }
        }
        return values;
    }

    @Override
    public String toString() {
        String board = "";
        for(int i = 1; i <= boardSize; i++) {
            board += "+ = ";
        }
        board += "+\n";
        int lineCounter = 1;
        for(SudokuRow row : rowsArray) {
            board += "|";
            for(SudokuField field : row.getFieldsArray()) {
                board += (field.getValue() == SudokuField.EMPTY) ? "   |" : " " + field.getValue() + " |";
            }
            board += "\n";

            if(lineCounter % 3 == 0){
                for(int i = 1; i <= row.getFieldsArray().size(); i++) {
                    board += "+ = ";
                }
            } else {
                for(int i = 1; i <= row.getFieldsArray().size(); i++) {
                    board += "+ - ";
                }
            }
            board += "+\n";
            lineCounter++;
        }
        return board;
    }

    public SudokuBoard deepCopy() throws CloneNotSupportedException {
        SudokuBoard clonedBoard = (SudokuBoard)super.clone();
        clonedBoard.rowsArray = new ArrayList<>();
        for(SudokuRow row : this.rowsArray) {
            SudokuRow clonedRow = new SudokuRow();
            for(SudokuField field : row.getFieldsArray()) {
                clonedRow.getFieldsArray().add(field.deepCopy());
            }
            clonedBoard.getRowsArray().add(clonedRow);
        }
        return clonedBoard;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rowsArray == null) ? 0 : rowsArray.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SudokuBoard other = (SudokuBoard) obj;
        if (rowsArray == null) {
            if (other.rowsArray != null)
                return false;
        } else if (!rowsArray.equals(other.rowsArray))
            return false;
        return true;
    }
}
