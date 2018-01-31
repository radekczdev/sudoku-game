package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;
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
}
