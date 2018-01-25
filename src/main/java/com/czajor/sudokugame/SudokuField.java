package com.czajor.sudokugame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SudokuField {
    // can EMPTY be public?
    public static int EMPTY = 0;
    private int value = EMPTY;
    private final List<Integer> possibleValues =
            new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

    public boolean setValue(final int value) {
        if(isValuePossible(value)) {
            this.value = value;
            return removePossibleValue(value);
        }
        System.out.println("Value cannot be changed to: " + value);
        return false;
    }

    public boolean setValueFromPossible() {
        return setValue(getPossibleValues().iterator().next());
    }

    public int getValue() {
        return value;
    }

    public boolean isValuePossible(int value) {
        return possibleValues.contains(value);
    }

    public List<Integer> getPossibleValues() {
        return possibleValues;
    }

    public boolean addPossibleValue(int value) {
        return possibleValues.add(value);
    }

    public boolean removePossibleValue(int value) {
        return possibleValues.remove(Integer.valueOf(value));
    }
}
