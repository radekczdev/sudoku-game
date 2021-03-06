package com.czajor.sudokugame.sections;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class SudokuRow {
    private final List<SudokuField> fieldsArray = new ArrayList<>(9);

    public SudokuRow(int rowSize) {
        IntStream.iterate(0, n -> n + 1).limit(rowSize).forEach(n -> fieldsArray.add(n, new SudokuField()));
        IntStream.iterate(0, n -> n + 1).limit(rowSize).forEach(n -> fieldsArray.get(n).setColumn(n));
    }
    
    public SudokuRow() {
    }

    public SudokuField getField(int n) {
        return fieldsArray.get(n);
    }

    public List<SudokuField> getFieldsArray() {
        return fieldsArray;
    }
}
