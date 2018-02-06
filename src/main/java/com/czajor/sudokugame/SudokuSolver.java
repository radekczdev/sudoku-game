package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SudokuSolver {
    private SudokuBoard board;
    private ArrayDeque<SudokuTemp> backtrack = new ArrayDeque<>();

    public SudokuSolver(SudokuBoard board) {
        this.board = board;
    }

    public SudokuBoard getBoard() {
        return board;
    }



    public void solveSudoku() {
        handleValidation();
        System.out.println("Current board state: \n" + board);
        if(isSolved()) {
            System.out.println("SUDOKU SOLVED!");
        }
        else {
            List<SudokuField> unresolvedFields = getEmptyFields();
            SudokuField currentField;
            for(int i = 0; i < unresolvedFields.size(); i++) {
                if(!isSolved()) {
                    currentField = unresolvedFields.get(i);
                    try {
                        backtrack.push(new SudokuTemp(board.deepCopy(), currentField.deepCopy()));
                        if(currentField.getPossibleValues().size() > 0) {
                            currentField.setNextPossibleValue();
                        } else {
                            throw new Exception();
                        }
                        validate();
                        System.out.println("i: " + i + board);

                    } catch (Exception e) {
                        System.out.println("Error during guessing value!");
                        board = backtrack.peekFirst().getBoardCopy();
                        currentField = backtrack.pop().getFieldCopy();
                        currentField.removeNextPossibleValue();     /// HERE LIES SOME PROBLEMS! Should be more simple
                       
                        if(backtrack.size() == 0) {
                            System.out.println("Sudoku cannot be solved!");
                            break;
                        }
                        i--;
                    }
                }
            }
        }
    }

    private List<SudokuField> getEmptyFields() {
        return board.getRowsArray().stream()
                .flatMap(n -> n.getFieldsArray().stream())
                .filter(n -> n.getValue() == SudokuField.EMPTY)
                .collect(Collectors.toList());
    }

    public boolean isSolved() {
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
                        if(!field.setNextPossibleValue()) {
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
