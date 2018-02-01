package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;
import com.czajor.sudokugame.sections.SudokuRow;

import java.util.*;
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
        try {
            checkRowsForDuplicates();
            checkColumnsForDuplicates();
            System.out.println(board.getField(1,0).getPossibleValues());
        } catch (Exception e) {
            System.out.println("There are no possible values for this Field!");
        }   
    }

    public void checkRowsForDuplicates(){
        for(SudokuRow row : board.getRowsArray()) {
            Set<Integer> writtenValues = getValuesFromRow(row);
            Set<Integer> previousFields = new HashSet<>();

            for(SudokuField field : row.getFieldsArray()) {
            	try {
            		writtenValues = checkForDuplicatesProcedure(field, writtenValues, previousFields);
            
	            } catch (Exception e) {
	                System.out.println("There are no possible values for this Field!");
	            }  
            row.removeWrittenValuesFromFields(writtenValues);
            }
        }
    }

    public void checkColumnsForDuplicates(){
    	for(int column = 0; column < board.getBoardSize(); column++) {
            Set<Integer> writtenValues = getValuesFromColumn(column);
            Set<Integer> previousFields = new HashSet<>();

            for(int row = 0; row < board.getBoardSize(); row++) {
            	SudokuField field = board.getField(row, column); 
System.out.println("Possible Values for element: row " + row + " column " + column + " : " + field.getPossibleValues());            	
            	try {
            		writtenValues = checkForDuplicatesProcedure(field, writtenValues, previousFields);
            	} catch (Exception e) {
                    System.out.println("There are no possible values for this Field!");
                }  
            	
            }
            board.removeWrittenValuesFromFieldsInColumns(writtenValues, column);
        }
    }
    
    public Set<Integer> checkForDuplicatesProcedure(SudokuField field, Set<Integer>writtenValues, Set<Integer> previousFields) throws Exception {
    	for(Integer value : writtenValues) {
            field.removePossibleValue(value);
        }
        if(field.getPossibleValues().size() == 1) {
            if(writtenValues.containsAll(field.getPossibleValues())) {
                throw new Exception();
            } else {
                field.setValueFromPossible();
            }
        }
        for(Integer previousValue : previousFields) {
            field.removePossibleValue(previousValue);
            if(field.getValue() == previousValue && field.getValue() != SudokuField.EMPTY) {
                field.setValueFromPossible();
            }
        }
        previousFields.add(field.getValue());
        writtenValues.add(field.getValue());
        return writtenValues;
    }


    public Set<Integer> getValuesFromRow(SudokuRow row) {
        return row.getFieldsArray().stream()
                .map(SudokuField::getValue)
                .filter(n -> n > 0)
                .sorted()
                .collect(Collectors.toSet());
    }

    public Set<Integer> getValuesFromColumn(int columnNumber) {
        Set<Integer> values = new HashSet<>();
        for(int i = 0; i < board.getBoardSize(); i++) {
            values.add(board.getFieldValue(i, columnNumber));
        }
        return values;
    }
}
