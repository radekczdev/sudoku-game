package com.czajor.sudokugame;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SudokuSolver {
    private SudokuBoard board;

    public SudokuSolver(SudokuBoard board) {
        this.board = board;
    }

    public SudokuBoard getBoard() {
        return board;
    }

    public void solveSudoku() {
        for(SudokuRow row : board.getRowsArray()) {
            Set<Integer> writtenValues = getWrittenValuesFromRow(row);

            List<Integer> previousFields = new ArrayList<>();

            for(SudokuField field : row.getFieldsArray()) {
                for(Integer value : writtenValues) {
                    field.removePossibleValue(value);
                }
                if(field.getValue() == SudokuField.EMPTY) {
                    field.setValueFromPossible();
                }
                for(Integer checkedValue : previousFields) {
                    if(checkedValue == field.getValue()) {
                        field.setValueFromPossible();
                    }
                }
                previousFields.add(field.getValue());
                writtenValues.add(field.getValue());
            }
        }
    }

    public Set<Integer> getWrittenValuesFromRow(SudokuRow row) {
        return row.getFieldsArray().stream()
                .map(SudokuField::getValue)
                .filter(n -> n > 0)
                .sorted()
                .collect(Collectors.toSet());
    }

}
