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

    public boolean setValue(final int value) {
        if(possibleValues.contains(value) && this.value == EMPTY) {
            this.value = value;
            return possibleValues.remove(value);
        }
        System.out.println("Value cannot be changed to: " + value);
        return false;
    }

    public void setPreviousValue(final int value) {
        this.value = value;
    }

    public boolean setNextPossibleValue() {
        return setValue(possibleValues.iterator().next());
    }
    
    public boolean removeNextPossibleValue() {
        if (possibleValues.iterator().hasNext()) {
            return possibleValues.remove(possibleValues.iterator().next());
        }
        return false;
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
        clonedField.possibleValues = new HashSet<>();
        for(Integer value : this.possibleValues) {
            clonedField.getPossibleValues().add((int) value);
        }
        return clonedField;
    }
}
