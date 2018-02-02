package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;

import java.util.Set;

public class SudokuValidator {
    public boolean newVersionOfCheck(SudokuBoard board) throws Exception {
        boolean takesAction = false;
        for(int row = 0; row < board.getBoardSize(); row++) {
            for(int column = 0; column < board.getBoardSize(); column++) {
                SudokuField field = board.getField(row, column);

                Set<Integer> possibleValuesInRow = board.getPossibleValuesFromRow(row);
                Set<Integer> possibleValuesInColumn = board.getPossibleValuesFromColumn(column);
                Set<Integer> possibleValuesInBlock = board.getPossibleValuesFromBlock(row, column);

                if(field.getValue() == SudokuField.EMPTY) {

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
