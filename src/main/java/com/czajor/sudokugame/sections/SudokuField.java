package com.czajor.sudokugame.sections;

import com.czajor.sudokugame.Prototype;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class SudokuField extends Prototype {
    public static int EMPTY = -1;
    private int value = EMPTY;
    private Set<Integer> possibleValues =
            new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    private int column;
    private int row;

    public boolean setValue(final int value) {
        if(possibleValues.contains(value) && this.value == EMPTY) {
            this.value = value;
            return possibleValues.remove(value);
        }
        System.out.println("Value cannot be changed to: " + value);
        return false;
    }

    public void setNextPossibleValue() {
        setValue(possibleValues.iterator().next());
    }
    
    public int getValue() {
        return value;
    }

    public Set<Integer> getPossibleValues() {
        return possibleValues;
    }

    public int getNextPossibleValue() {
        return possibleValues.iterator().next();
    }

    public void removePossibleValue(int value) {
        possibleValues.remove(value);
    }

    public SudokuField deepCopy() throws CloneNotSupportedException {
        SudokuField clonedField = (SudokuField) super.clone();
        clonedField.possibleValues = new HashSet<>();
        for(Integer value : this.possibleValues) {
            clonedField.getPossibleValues().add(value);
        }
        return clonedField;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
