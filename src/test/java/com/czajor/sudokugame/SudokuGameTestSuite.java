package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;

import org.junit.Assert;
import org.junit.Test;

public class SudokuGameTestSuite {
    @Test
    public void testSudokuBoardToString() {
        // Given
        SudokuBoard board = new SudokuBoard();

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
	public void testSetFieldValueWithSudokuSolver() {
    	GamePlay gamePlay = new GamePlay();
    	String[] values = {"117", "125", "181", "193", "311", "329", "345", "366", "382", "398"
    		, "415", "444", "456", "469", "491", "555", "612", "647", "653", "661", "694"
			, "718", "723", "742", "764", "786", "799", "916", "924", "985", "997"};

    	for(String value : values) {
    		gamePlay.setFieldValue(value);
		}

		SudokuSolver solver = new SudokuSolver(gamePlay.getBoard());
		solver.solveSudoku();
		System.out.println(solver.getBoard());
	}
}
