package com.czajor.sudokugame.sections;

import java.util.*;

public final class SudokuField {
    public static int EMPTY = -1;
    private int value = EMPTY;
    private final Set<Integer> possibleValues =
            new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

    public boolean setValue(final int value) {
        if(isValuePossible(value) && this.value == EMPTY) {
            this.value = value;
            return removePossibleValue(value);
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

    public boolean isValuePossible(int value) {
        return possibleValues.contains(value);
    }

    public Set<Integer> getPossibleValues() {
        return possibleValues;
    }

    public boolean removePossibleValue(int value) {
        return possibleValues.remove(value);
    }
}
