package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
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
        System.out.println("Final result after solveSudoku(): \n" + solver.getBoard());

        // When
        Boolean isSolved = solver.isSolved();

        // Then
        Assert.assertTrue(isSolved);

    }

    @Test
    public void testSetFieldValueWithSudokuSolver3() {
        // Given

        GamePlay gamePlay = new GamePlay();
        String[] values = {"149", "172", "198", "247", "285", "327", "368", "379", "386", "457", "475", "511", "544"
                , "563", "592", "635", "658", "724", "738", "746", "783", "822", "867", "913", "937", "965"};

        for(String value : values) {
            gamePlay.setFieldValue(value);
        }

        SudokuSolver solver = new SudokuSolver(gamePlay.getBoard());
        solver.solveSudoku();
        System.out.println("Final result after solveSudoku(): \n" + solver.getBoard());

        // When
        Boolean isSolved = solver.isSolved();

        // Then
        Assert.assertTrue(isSolved);

    }

    @Test
    public void testSetFieldValueWithSudokuSolver4() {
        // Given

        GamePlay gamePlay = new GamePlay();
        String[] values = {
                "121", "168", "193", "234", "259", "263", "316", "328", "414", "452", "488", "515", "599", "629"
                , "653", "696", "784", "795", "841", "854", "879", "912", "946", "981"
        };

        for(String value : values) {
            gamePlay.setFieldValue(value);
        }

        SudokuSolver solver = new SudokuSolver(gamePlay.getBoard());
        solver.solveSudoku();
        System.out.println("Final result after solveSudoku(): \n" + solver.getBoard());

        // When
        Boolean isSolved = solver.isSolved();

        // Then
        Assert.assertTrue(isSolved);

    }

    @Test
    public void testSetFieldValueWithSudokuSolver5() {
        // Given

        GamePlay gamePlay = new GamePlay();
        String[] values = {
                "131", "153", "168", "187", "241", "298", "376", "384", "449", "454", "493", "539", "574", "611"
                , "657", "666", "722", "733", "816", "865", "925", "943", "958", "972"
        };

        for(String value : values) {
            gamePlay.setFieldValue(value);
        }

        SudokuSolver solver = new SudokuSolver(gamePlay.getBoard());
        solver.solveSudoku();
        System.out.println("Final result after solveSudoku(): \n" + solver.getBoard());

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
        int testValueInBoard = board.getField(0, 8).getValue();
        int testValueInClonedBoard = clonedBoard.getField(0, 8).getValue();

        boolean isEqual = board.equals(clonedBoard);

        // Then
        Assert.assertFalse(isEqual);
        Assert.assertEquals(1, testValueInBoard);
        Assert.assertEquals(5, testValueInClonedBoard);

    }

    @Test
    public void testDivision() {
        System.out.println();
        int blockSize = 3;
        int row = 3;
        int column = 2;
        int rowMod = (row / blockSize) * blockSize;
        int columnMod = (column / blockSize) * blockSize;

        System.out.println(rowMod);
        System.out.println(columnMod);
    }
}
