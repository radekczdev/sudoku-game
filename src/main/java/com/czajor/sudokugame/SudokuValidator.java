package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;
import com.czajor.sudokugame.sections.SudokuRow;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {
    public void checkRowsForDuplicates(SudokuBoard board){
        for(int row = 0; row < board.getBoardSize(); row++) {
            Set<Integer> valuesInRows = board.getValuesFromRow(row);
            Set<Integer> previousFields = new HashSet<>();

            for(int column = 0; column < board.getBoardSize(); column++) {
                Set<Integer> valuesInColumns = board.getValuesFromColumn(column);
                Set<Integer> valuesInBlock = board.getValuesFromBlock(row, column);
                SudokuField field = board.getField(row,column);
                if(field.getValue() == SudokuField.EMPTY) {
                    try {
                        valuesInRows = checkForDuplicatesProcedure(field, valuesInRows, valuesInColumns, valuesInBlock, previousFields);

                    } catch (Exception e) {
                        System.out.println("There are no possible values for this Field!");
                    }
                }
//            board.getRowsArray().get(row).removeWrittenValuesFromFields(valuesInRows);
            }
        }
    }

    public void checkColumnsForDuplicates(SudokuBoard board){
        for(int column = 0; column < board.getBoardSize(); column++) {
            Set<Integer> valuesInColumns = board.getValuesFromColumn(column);
            Set<Integer> previousFields = new HashSet<>();

            for(int row = 0; row < board.getBoardSize(); row++) {
                Set<Integer> valuesInRows = board.getValuesFromRow(row);
                Set<Integer> valuesInBlock = board.getValuesFromBlock(row, column);
                SudokuField field = board.getField(row, column);
                if(field.getValue() == SudokuField.EMPTY) {
                    try {
                        valuesInColumns = checkForDuplicatesProcedure(field, valuesInColumns, valuesInRows, valuesInBlock, previousFields);
                    } catch (Exception e) {
                        System.out.println("There are no possible values for this Field!");
                    }
                }
            }
//            board.removeWrittenValuesFromFieldsInColumns(valuesInColumns, column);
        }
    }

    public Set<Integer> checkForDuplicatesProcedure(SudokuField field, Set<Integer> writtenValues1, Set<Integer> writtenValues2, Set<Integer> writtenValues3, Set<Integer> previousFields) throws Exception {
        for(Integer value : writtenValues1) {
            field.removePossibleValue(value);
        }
        if(field.getPossibleValues().size() == 1) {
            if(writtenValues1.containsAll(field.getPossibleValues())) {
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

        for(Integer value : field.getPossibleValues()) {
            if(!writtenValues1.contains(value) && !writtenValues2.contains(value) && !writtenValues3.contains(value)) {
                field.setValue(value);
                break;
            }
        }

        previousFields.add(field.getValue());
        writtenValues1.add(field.getValue());
        return writtenValues1;
    }
}