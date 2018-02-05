package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SudokuSolver {
    private SudokuBoard board;
    private List<SudokuTemp> backtrack = new ArrayList<>();

    public SudokuSolver(SudokuBoard board) {
        this.board = board;
    }

    public SudokuBoard getBoard() {
        return board;
    }



    public void solveSudoku() {
        handleValidation();
        if(isSolved()) {
            System.out.println("SUDOKU SOLVED!");
        }
//        else {
//            List<SudokuField> unresolvedFields = getEmptyFields();
//            while(unresolvedFields.iterator().hasNext() && !isSolved()) {
//                SudokuField currentField = unresolvedFields.iterator().next();
//                currentField.setValueFromPossible();
//
//                handleValidation();
//            }
//
//        }

    }

    private List<SudokuField> getEmptyFields() {
        return board.getRowsArray().stream()
                .flatMap(n -> n.getFieldsArray().stream())
                .filter(n -> n.getValue() == SudokuField.EMPTY)
                .collect(Collectors.toList());
    }

    private boolean isSolved() {
        long count = board.getRowsArray().stream()
                .flatMap(n -> n.getFieldsArray().stream())
                .map(SudokuField::getValue)
                .filter(n -> n == SudokuField.EMPTY)
                .count();
        return count == 0;
    }

    private void handleValidation() {
        try {
            while(validate()){

            }
        } catch (Exception e) {
            System.out.println("There are no possible values for this Field!");
        }
    }

    private boolean validate() throws Exception {
        boolean takesAction = false;
        for(int row = 0; row < board.getBoardSize(); row++) {
            for(int column = 0; column < board.getBoardSize(); column++) {
                SudokuField field = board.getField(row, column);

                if(field.getValue() == SudokuField.EMPTY) {
                    Set<Integer> possibleValuesInRow = board.getPossibleValuesFromRow(row);
                    Set<Integer> possibleValuesInColumn = board.getPossibleValuesFromColumn(column);
                    Set<Integer> possibleValuesInBlock = board.getPossibleValuesFromBlock(row, column);

                    Set<Integer> valuesInRow = board.getValuesFromRow(row);
                    Set<Integer> valuesInColumn = board.getValuesFromColumn(column);
                    Set<Integer> valuesInBlock = board.getValuesFromBlock(row, column);

                    if(field.getPossibleValues().removeAll(valuesInRow) ||
                            field.getPossibleValues().removeAll(valuesInColumn) ||
                            field.getPossibleValues().removeAll(valuesInBlock)) {
                        takesAction = true;
                    }

                    for(Integer value : field.getPossibleValues()) {
                        if(!possibleValuesInRow.contains(value)
                                && !possibleValuesInColumn.contains(value)
                                && !possibleValuesInBlock.contains(value)) {
                            field.setValue(value);
                            takesAction = true;
                            break;
                        }
                    }

                    if(field.getPossibleValues().size() == 1) {
                        if(!field.setValueFromPossible()) {
                            throw new Exception("There are no possible values for this Field!");
                        }
                        takesAction = true;
                    }
                }
            }
        }
        return takesAction;
    }
}
