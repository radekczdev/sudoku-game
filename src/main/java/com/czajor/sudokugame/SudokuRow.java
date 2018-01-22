package com.czajor.sudokugame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class SudokuRow {
    private final List<SudokuField> fieldsArray = new ArrayList<>(9);

    public SudokuRow(int rowSize) {
        IntStream.iterate(0, n -> n + 1).limit(rowSize).forEach(n -> fieldsArray.add(n, new SudokuField()));
    }

    public void setField(int index, SudokuField field) {
        fieldsArray.set(index, field);
    }

    public List<SudokuField> getFieldsArray() {
        return fieldsArray;
    }
}
