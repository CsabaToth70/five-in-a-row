package com.codecool.fiveinarow;

import java.util.Scanner;
import java.util.Random;

public class Game implements GameInterface {

    private int[][] board;
    private String[][] moveList;

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

    public void moveBoard() {
        char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String[][] moveList = new String[board.length][board[0].length];
        int Counter1 = 0;
        for (String[] row : moveList) {
            int Counter2 = 0;
            for (String cell : row) {
                moveList[Counter1][Counter2] = abc[Counter1] + String.valueOf(Counter2 + 1);
                Counter2++;
            }
            Counter1++;
        }
        setMoveList(moveList);
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public String[][] getMoveList() {
        return moveList;
    }

    public void setMoveList(String[][] moveList) {
        this.moveList = moveList;
    }

    public int[] getMove(int player) {
        boolean valid = false;
        Scanner myObj = new Scanner(System.in);
        String move;
        System.out.println("Enter your move player " + player + ": ");
        move = myObj.nextLine();
        System.out.println();
        while (!valid) {
            for (String[] row : moveList) {
                for (String cell : row) {
                    if (cell.equals(move)) {
                        valid = true;
                    }
                }
            }

            if (move.equals("Quit") || move.equals("quit")) {
                System.out.println("Thank you for playing!");
                System.exit(0);
            }

            if (!valid) {
                System.out.println("Invalid input or position is already taken");
                System.out.println("Enter your move player " + player + ": ");
                move = myObj.nextLine();
            }

        }

        int[] coordinates = {0, 0};

        int Counter = 0;
        for (String[] row : moveList) {
            int Counter2 = 0;
            for (String cell : row) {
                if (cell.equals(move)) {
                    moveList[Counter][Counter2] = "---";
                    coordinates = new int[] {Counter, Counter2};
                }
                Counter2++;
            }
            Counter++;
        }

        return coordinates;
    }

    public String aiDecision(){
        char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String move="";
        Random rand = new Random();
        char randRowString = '#';
        String randColString = "";
        int randRowInt = rand.nextInt(board.length-1);
        randRowString = abc[randRowInt];
        int randColInt = rand.nextInt(board[0].length-1);
        randColString = String.valueOf(randColInt);
        move = randRowString + randColString;
        return move;
    }

    public int[] getAiMove(int player) {
        boolean valid = false;
        String move;
        System.out.println("Enter your move player " + player + ": ");
        move = aiDecision(); // AI input pl. A4
        while (!valid) {
            for (String[] row : moveList) {
                for (String cell : row) {
                    if (cell.equals(move)) {
                        valid = true;
                    }
                }
            }
            if (!valid) {
                move = aiDecision(); // AI input pl. A5
            }
        }
        int[] coordinates = {0, 0};
        int Counter = 0;
        for (String[] row : moveList) {
            int Counter2 = 0;
            for (String cell : row) {
                if (cell.equals(move)) {
                    moveList[Counter][Counter2] = "---";
                    coordinates = new int[] {Counter, Counter2};
                }
                Counter2++;
            }
            Counter++;
        }
        return coordinates;
    }

//    public int[] checkMiddleCell(int player, int lineLength, int rowToCheck, int colToCheck){
//        int[] resultList = new int[] {0, -1, -1, -1};
//        int distance= (lineLength/2)+1;
//        int counter = 0;
//        int playerCounter = 0;
//
//        int upSigns = checkDirection(player, distance, rowToCheck, colToCheck, playerCounter, 0)[0];
//        int downSigns = checkDirection(player, distance, rowToCheck, colToCheck, playerCounter, 4)[0];
//        int verticalSigns = upSigns + downSigns;
//
//        int leftSigns = checkDirection(player, distance, rowToCheck, colToCheck, playerCounter, 6)[0];
//        int rightSigns = checkDirection(player, distance, rowToCheck, colToCheck, playerCounter, 2)[0];
//        int horizontalSigns = leftSigns + rightSigns;
//
//        int upLeftSigns = checkDirection(player, distance, rowToCheck, colToCheck, playerCounter, 7)[0];
//        int downRightSigns = checkDirection(player, distance, rowToCheck, colToCheck, playerCounter, 3)[0];
//        int backSlashSigns = upLeftSigns + downRightSigns;
//
//
//        return resultList;
//    }

    public void mark(int player, int row, int col) {
        int Counter = 0;
        for (int[] actualRow : board) {
            int Counter2 = 0;
            for (int actualCell : actualRow) {
                if (Counter == row && Counter2 == col) {
                    board[Counter][Counter2] = player;
                }
                Counter2++;
            }
            Counter++;
        }
    }

    private int[] checkDirection(int player, int lineLength, int row, int col, int playerCounter, int direction){
        // direction= 0:up, 1:up-right, 2:right, 3:down-right, 4:down, 5:down-left, 6:left, 7:up-left
        // checkResult= 0:playerCounter, 1:row, 2:col, 3:direction as int
        int[] checkResult = new int[] {0, -1, -1, direction};
            for (int i = 0; i > (lineLength * (-1)); i--) {
                if (direction == 0) {
                    if (row + i >= 0) {
                        if (board[row + i][col] == player) {
                            playerCounter++;
                        }
                        if (playerCounter == lineLength - 1 && board[row + i][col] == 0) {
                            checkResult[1] = row + i;
                            checkResult[2] = col;
                        }
                    }
                } else if (direction == 1) {
                    if (row + i >= 0 && (col + i * (-1)) < board[0].length) {
                        if (board[row + i][col + i * (-1)] == player) {
                            playerCounter++;
                        }
                        if (playerCounter == lineLength - 1 && board[row + i][col + i * (-1)] == 0) {
                            checkResult[1] = row + i;
                            checkResult[2] = col + i * (-1);
                        }
                    }
                } else if (direction == 2) {
                    if (col - i < board[0].length) {
                        if (board[row][col - i] == player) {
                            playerCounter++;
                        }
                        if (playerCounter == lineLength - 1 && board[row][col - i] == 0) {
                            checkResult[1] = row;
                            checkResult[2] = col - i;
                        }
                    }
                } else if (direction == 3) {
                    if (row - i < board.length && col - i < board[0].length) {
                        if (board[row - i][col - i] == player) {
                            playerCounter++;
                        }
                        if (playerCounter == lineLength - 1 && board[row - i][col - i] == 0) {
                            checkResult[1] = row - i;
                            checkResult[2] = col - i;
                        }
                    }
                } else if (direction == 4) {
                    if (row - i < board.length) {
                        if (board[row - i][col] == player) {
                            playerCounter++;
                        }
                        if (playerCounter == lineLength - 1 && board[row - i][col] == 0) {
                            checkResult[1] = row - i;
                            checkResult[2] = col;
                        }
                    }
                } else if (direction == 5) {
                    if(row + i * (-1) < board.length && col + i >= 0){
                        if (board[row + i * (-1)][col + i] == player) {
                            playerCounter++;
                        }
                        if (playerCounter == lineLength - 1 && board[row + i * (-1)][col + i] == 0) {
                            checkResult[1] = row + i * (-1);
                            checkResult[2] = col + i;
                        }
                    }
                } else if (direction == 6) {
                    if(col + i >= 0){
                        if (board[row][col + i] == player) {
                            playerCounter++;
                        }
                        if (playerCounter == lineLength - 1 && board[row][col + i] == 0) {
                            checkResult[1] = row;
                            checkResult[2] = col + i;
                        }
                    }
                } else if (direction == 7) {
                    if(row + i >= 0 && col + i >= 0){
                        if (board[row + i][col + i] == player) {
                            playerCounter++;
                        }
                        if (playerCounter == lineLength - 1 && board[row + i][col + i] == 0) {
                            checkResult[1] = row + i;
                            checkResult[2] = col + i;
                        }
                    }
                }
            }
        checkResult[0] = playerCounter;
        return checkResult;
    }

    public boolean hasWon(int player, int howMany) {
        boolean result = false;
            int counter = 0;
            int playerCounter = 0;
            for (int[] row : board) {
                int counter2 = 0;
                for (int cell : row) {
                    if (player == cell) {
                        for(int j=0; j<8; j++) { // check the lines all 8 different directions
                            playerCounter = checkDirection(player, howMany, counter, counter2, playerCounter, j)[0];
                            if (playerCounter == howMany) {
                                result = true;
                            }
                            playerCounter = 0;
                        }
                    }
                    counter2++;
                }
                counter++;
            }
        if(result){
            return true;
        }
        return false;
    }

    public boolean isFull() {
        int Counter = 0;
        int nullCounter = 0;
        for (int[] row : board) {
            int Counter2 = 0;
            for (int cell : row) {
                if(board[Counter][Counter2] == 0){
                    nullCounter++;
                }
                Counter2++;
            }
            Counter++;
        }
        if(nullCounter == 0){
            return true;
        }
        return false;
    }

    public void printBoard() {
        char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String header = " ";
        for (int i=0; i<= board.length; i++){
            if(i==0){
                header = header + "  ";
            } else if (i > 0 && i < 10){
                header = header + i + "  ";
            } else if (i >= 10){
                header = header + i + " ";
            }
        }
        System.out.println(header);
        String rowToPrint = "";
        int counter = 0;
        for(int[] row: board){
            rowToPrint = rowToPrint + abc[counter] + "  ";
            for(int cell: row){
                if(cell==0){
                    rowToPrint = rowToPrint + ".  ";
                } else if ( cell == 1){
                    rowToPrint = rowToPrint + "X  ";
                } else if ( cell == 2){
                    rowToPrint = rowToPrint + "O  ";
                }
            }
            System.out.println(rowToPrint);
            rowToPrint = "";
            counter++;
        }
    }

    public void printResult(int player) {
        if(player == 0){
            System.out.println("It's a tie!");
            System.exit(0);
        } else {
            System.out.println("Player " + player + " has won!");
            System.exit(0);
        }
    }

    public int[] enableAi(int player) {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException ie){
            Thread.currentThread().interrupt();
        }
        int[] coordinates = getAiMove(player);
        return coordinates;
    }

    public void play(int howMany) {
        moveBoard();
        int currentPlayer = 1;
        int AIPlayer = 1;
        printBoard();
        while (!isFull() || !hasWon(currentPlayer, howMany)) {
            int[] actualCoordinate;
            if (currentPlayer == AIPlayer) {
                actualCoordinate = enableAi(currentPlayer);
            } else {
                actualCoordinate = getMove(currentPlayer);
            }
            mark(currentPlayer, actualCoordinate[0], actualCoordinate[1]);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            printBoard();
            if (hasWon(currentPlayer, howMany)){
                printResult(currentPlayer);
            }
            if (isFull()){
                printResult(0);
            }
            if (currentPlayer == 1) {
                currentPlayer = 2;
            }
            else {
                currentPlayer = 1;
            }
        }
    }
}