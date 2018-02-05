package com.czajor.sudokugame.sections;

import com.czajor.sudokugame.Prototype;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class SudokuField extends Prototype {
    public static int EMPTY = -1;
    private int value = EMPTY;
    private final Set<Integer> possibleValues =
            new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

    public boolean setValue(final int value) {
        if(possibleValues.contains(value) && this.value == EMPTY) {
            this.value = value;
            return possibleValues.remove(value);
        }
        System.out.println("Value cannot be changed to: " + value);
        return false;
    }

    public boolean setValueFromPossible() {
        boolean isSet = possibleValues.iterator().hasNext();
        value = possibleValues.iterator().next();
        return isSet;
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

    public SudokuField deepCopy() throws CloneNotSupportedException {
        SudokuField clonedField = (SudokuField) super.clone();
        clonedField.possibleValues.addAll(possibleValues);
        return clonedField;
    }
}
