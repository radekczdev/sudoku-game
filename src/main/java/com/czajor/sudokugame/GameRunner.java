package com.czajor.sudokugame;

public class GameRunner {
    public static void main(String[] args) {
        boolean gameIsFinished = false;
        while(!gameIsFinished) {
            GamePlay gamePlay = new GamePlay();
            gameIsFinished = gamePlay.solveSudoku();
        }
    }
}
