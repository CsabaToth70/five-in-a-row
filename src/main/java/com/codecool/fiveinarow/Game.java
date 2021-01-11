package com.codecool.fiveinarow;

import java.util.Arrays;
import java.util.Scanner;

public class Game implements GameInterface {

    private int[][] board;

    public Game(int nRows, int nCols) {
        int[][] board = new int[nRows][nCols];
        int Counter = 0;
        for (int[] row : board) {
            int Counter2 = 0;
            for (int cell : row) {
                board[Counter][Counter2] = 0;
                Counter2++;
            }
            Counter++;
        }
        setBoard(board);
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[] getMove(int player) {
        char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String[][] moveList = new String[board.length][board[0].length];
        int Counter = 0;
        for (String[] row : moveList) {
            int Counter2 = 0;
            for (String cell : row) {
                moveList[Counter][Counter2] = abc[Counter] + String.valueOf(Counter2 + 1);
                Counter2++;
            }
            Counter++;
        }

        boolean valid = false;
        Scanner myObj = new Scanner(System.in);
        String move;
        System.out.println("Enter your move: ");
        move = myObj.nextLine();

        while (!valid) {
            for (String[] row : moveList) {
                for (String cell : row) {
                    if (cell.equals(move)) {
                        valid = true;
                    }
                }
            }
            if (!valid) {
                System.out.println("Invalid input or position is already taken");
                System.out.println("Enter your move: ");
                move = myObj.nextLine();
            }
        }

        Counter = 0;
        for (String[] row : moveList) {
            int Counter2 = 0;
            for (String cell : row) {
                if (cell.equals(move)) {
                    moveList[Counter][Counter2] = "---";
                }
                Counter2++;
            }
            Counter++;
        }
        System.out.println(Arrays.deepToString(moveList));

        return null;
    }

    public int[] getAiMove(int player) {
        return null;
    }

    public void mark(int player, int row, int col) {
    }

    public boolean hasWon(int player, int howMany) {
        return false;
    }

    public boolean isFull() {
        return false;
    }

    public void printBoard() {
    }

    public void printResult(int player) {
    }

    public void enableAi(int player) {
    }

    public void play(int howMany) {
    }
}
