package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;

import java.util.*;
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
        handleValidation();
        System.out.println("Current board state: \n" + board);
        if(isSolved()) {
            System.out.println("SUDOKU SOLVED!");
        }
        else {
            Iterator<SudokuField> iterator;
            SudokuField currentField;
            do{
                iterator = getEmptyFields().iterator();
                currentField = iterator.next();
                try {
                    backtrack.push(new SudokuTemp(board.deepCopy(), currentField));
                    if(currentField.getPossibleValues().size() > 0) {
                        currentField.setNextPossibleValue();
                    }
                    handleValidation();
                    System.out.println(board);
                } catch (Exception e) {
                    System.out.println("Error during guessing value!");
                    SudokuTemp lastSavedGame = backtrack.pop();
                    try {
                        board = lastSavedGame.getBoardCopy().deepCopy();
                    } catch (Exception copyBoardError) {
                        System.out.println(copyBoardError);
                    }
                    board.getField(lastSavedGame.getRow(), lastSavedGame.getColumn()).getPossibleValues().remove(lastSavedGame.getValue());
                    if(backtrack.isEmpty()) {
                        System.out.println("Sudoku cannot be solved!");
                        break;
                    }
                }
            } while (!isSolved() && iterator.hasNext());
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

//                if(field.getPossibleValues().size() == 0) {
//                    throw new Exception("There are no possible values for this Field!");
//                }

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
