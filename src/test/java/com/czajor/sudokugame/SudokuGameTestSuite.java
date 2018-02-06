package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
import com.czajor.sudokugame.sections.SudokuField;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SudokuGameTestSuite {
    
    @Before
    public void beforeTest() {
        System.out.println("============================");
    }
       
    @Test
	public void testSetFieldValueWithSudokuSolver1() {
        // Given
    	GamePlay gamePlay = new GamePlay();
    	String[] values = {"117", "125", "181", "193", "311", "329", "345", "366", "382", "398"
    		, "415", "444", "456", "469", "491", "555", "612", "647", "653", "661", "694"
			, "718", "723", "742", "764", "786", "799", "916", "924", "985", "997"};

    	for(String value : values) {
    		gamePlay.setFieldValue(value);
		}

		SudokuSolver solver = new SudokuSolver(gamePlay.getBoard());
		solver.solveSudoku();
		
		// When
		Boolean isSolved = solver.isSolved();
		System.out.println(solver.getBoard());
		
		// Then
		Assert.assertTrue(isSolved);
	}
    
    @Test
    public void testSetFieldValueWithSudokuSolver2() {
        // Given
        
        GamePlay gamePlay = new GamePlay();
        String[] values = {"123", "191", "215", "246", "251", "365", "489", "529", "612", "658"
                , "781", "811", "843", "987" };

        for(String value : values) {
            gamePlay.setFieldValue(value);
        }

        SudokuSolver solver = new SudokuSolver(gamePlay.getBoard());
        solver.solveSudoku();
        System.out.println("Field 4,8 value: " + gamePlay.getBoard().getFieldValue(4,8));
        System.out.println("Field 4,8 poss values: " + gamePlay.getBoard().getField(4,8).getPossibleValues());
        System.out.println("Final result after solveSUdoku(): \n" + gamePlay.getBoard());
        
        // When
        Boolean isSolved = solver.isSolved();
        
        // Then
        Assert.assertTrue(isSolved);
        
    }
    
    @Test
    public void testDeepCloneOfBoard() throws Exception {
        // Given
        SudokuBoard board = new SudokuBoard();
        board.setFieldValue(0, 0, 8);
        board.setFieldValue(4, 4, 4);
        SudokuBoard clonedBoard = board.deepCopy();
        
        board.setFieldValue(0, 8, 1);
        clonedBoard.setFieldValue(0, 8, 5);
        
        // When
        System.out.println(board);
        System.out.println(clonedBoard);
        int testValueInBoard = board.getFieldValue(0, 8);
        int testValueInClonedBoard = clonedBoard.getFieldValue(0, 8);
       
        boolean isEqual = board.equals(clonedBoard);
        
        // Then
        Assert.assertFalse(isEqual);
        Assert.assertEquals(1, testValueInBoard);
        Assert.assertEquals(5, testValueInClonedBoard);
        
    }
}
