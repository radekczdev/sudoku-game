package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SudokuSolver {
    private SudokuBoard board;
    private Deque<SudokuTemp> backtrack = new ArrayDeque<>();

    public SudokuSolver(SudokuBoard board) {
        this.board = board;
    }

    public SudokuBoard getBoard() {
        return board;
    }

    public void solveSudoku() {
        SudokuField currentField;
        int iterations = 0;
        while (!isSolved()){
            iterations++;
            currentField = getEmptyFields().iterator().next();
            try {
                backtrack.push(new SudokuTemp(board.deepCopy(), currentField));
                while(validate());
                if(currentField.getPossibleValues().size() > 0) {
                    currentField.setNextPossibleValue();
                }
            } catch (Exception e) {
                if(backtrack.isEmpty()) {
                    System.out.println("Sudoku cannot be solved!");
                    break;
                }
                SudokuTemp lastSavedGame = backtrack.pop();
                board = lastSavedGame.getBoardCopy();
                board.getField(lastSavedGame.getRow(), lastSavedGame.getColumn()).removePossibleValue(lastSavedGame.getValue());
            }
        }
        if(isSolved()) {
            System.out.println("SUDOKU SOLVED in " + iterations + " iterations");
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

    private boolean removeExistingValues (SudokuField field) {
        int row = field.getRow();
        int column = field.getColumn();

        Set<Integer> valuesInRow = board.getValuesFromRow(row);
        Set<Integer> valuesInColumn = board.getValuesFromColumn(column);
        Set<Integer> valuesInBlock = board.getValuesFromBlock(row, column);

        return field.getPossibleValues().removeAll(valuesInRow) ||
                field.getPossibleValues().removeAll(valuesInColumn) ||
                field.getPossibleValues().removeAll(valuesInBlock);
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

                    takesAction = removeExistingValues(field);

                    if(field.getPossibleValues().size() == 1 ) {
                        if(!board.setFieldValue(row,column,field.getNextPossibleValue())) {
                            throw new Exception("There are no possible values for this Field!");
                        }
                        takesAction = true;
                    }

                    for(Integer value : field.getPossibleValues()) {
                        if(!possibleValuesInRow.contains(value)
                                && !possibleValuesInColumn.contains(value)
                                && !possibleValuesInBlock.contains(value)) {
                            field.setValue(value);
                            takesAction = true;
                        }
                    }
                }
            }
        }
        return takesAction;
    }
}
