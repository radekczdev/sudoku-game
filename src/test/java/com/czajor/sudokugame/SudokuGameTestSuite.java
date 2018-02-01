package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;

import org.junit.Assert;
import org.junit.Test;

public class SudokuGameTestSuite {
    @Test
    public void testSudokuBoardToString() {
        // Given
        SudokuBoard board = new SudokuBoard(9);

        // When
        board.setFieldValue(1,2,4);
        board.setFieldValue(5,4,8);
        board.setFieldValue(5,4,8);
        board.setFieldValue(5,4,4);

        // When
        System.out.println(board);

        // Then
        Assert.assertEquals(9, board.getRowsArray().size());
        Assert.assertEquals(9, board.getRowsArray().get(0).getFieldsArray().size());
    }
    
    @Test
    public void testBoardWithData() throws Exception {
    	// Given    	
    	SudokuField field11 = new SudokuField();
    	field11.setValue(3);
    	
    	SudokuField field12 = new SudokuField();
    	field12.setValue(3);
    	
    	SudokuField field13 = new SudokuField();
    	field13.setValue(3);
    	
    	SudokuField field21 = new SudokuField();
    	field21.setValue(3);
    	
    	SudokuField field22 = new SudokuField();
    	field22.setValue(3);
    	
    	SudokuField field32 = new SudokuField();
    	field32.setValue(4);
    	
    	SudokuField field42 = new SudokuField();
    	field42.setValue(5);
    	
    	SudokuField field52 = new SudokuField();
    	field52.setValue(6);
    	
    	SudokuField field62 = new SudokuField();
    	field62.setValue(7);
    	
    	SudokuField field72 = new SudokuField();
    	field72.setValue(8);
    	
    	SudokuField field82 = new SudokuField();
    	field82.setValue(9);
    	
    	SudokuField field92 = new SudokuField();
    	field92.setValue(3);
    	
    	SudokuBoard board = new SudokuBoard(9);
    	
    	// When
    	board.getRowsArray().get(0).getFieldsArray().set(0, field11);
    	board.getRowsArray().get(0).getFieldsArray().set(1, field12);
    	board.getRowsArray().get(0).getFieldsArray().set(2, field13);
    	
    	board.getRowsArray().get(1).getFieldsArray().set(0, field21);
    	board.getRowsArray().get(1).getFieldsArray().set(1, field22);
    	
    	board.getRowsArray().get(2).getFieldsArray().set(1, field32);
    	board.getRowsArray().get(3).getFieldsArray().set(1, field42);
    	board.getRowsArray().get(4).getFieldsArray().set(1, field52);
    	board.getRowsArray().get(5).getFieldsArray().set(1, field62);
    	board.getRowsArray().get(6).getFieldsArray().set(1, field72);
    	board.getRowsArray().get(7).getFieldsArray().set(1, field82);
    	board.getRowsArray().get(8).getFieldsArray().set(1, field92);
    	
    	SudokuSolver solver = new SudokuSolver(board);
    	
    	solver.solveSudoku();
    	System.out.println(board);
    	
    	
    	// Then
    	
    }
}
