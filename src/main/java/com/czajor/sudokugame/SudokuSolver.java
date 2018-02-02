package com.czajor.sudokugame;

import com.czajor.sudokugame.sections.SudokuBoard;

public class SudokuSolver {
    private SudokuBoard board;

    public SudokuSolver(SudokuBoard board) {
        this.board = board;
    }

    public SudokuBoard getBoard() {
        return board;
    }

    public void solveSudoku() {
        SudokuValidator validator = new SudokuValidator();
        try {
            validator.newVersionOfCheck(board);
//            validator.checkRowsForDuplicates(board);
//            validator.checkColumnsForDuplicates(board);
//            validator.checkBlockForDuplicates(board);
        } catch (Exception e) {
            System.out.println("There are no possible values for this Field!");
        }   
    }
}
