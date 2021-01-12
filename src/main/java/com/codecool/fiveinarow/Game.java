package com.codecool.fiveinarow;

import java.util.Arrays;
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

    private boolean checkUp(int player, int howMany) {
        int counter = 0;
        int playerCounter = 0;
        boolean result = false;
        for (int[] row : board) {
            int counter2 = 0;
            for (int cell : row) {
                if(player == cell){
                    for(int i=0; i > (howMany*(-1)); i--){
                        if(counter+i >= 0){
                            if(board[counter+i][counter2] == player){
                                playerCounter++;
                            }
                        }
                    }
                    if(playerCounter == howMany){
                        result = true;
                    }
                    playerCounter = 0;
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

    private boolean checkUpRight(int player, int howMany) {
        int counter = 0;
        int playerCounter = 0;
        boolean result = false;
        for (int[] row : board) {
            int counter2 = 0;
            for (int cell : row) {
                if(player == cell) {
                    for (int i = 0; i > (howMany * (-1)); i--) {
                        if(counter + i >= 0 && (counter2 + i * (-1)) < board[0].length){
                            if (board[counter + i][counter2 + i * (-1)] == player) {
                                playerCounter++;
                            }
                        }
                    }
                    if(playerCounter == howMany){
                        result = true;
                    }
                    playerCounter = 0;
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

    private boolean checkUpLeft(int player, int howMany) {
        int counter = 0;
        int playerCounter = 0;
        boolean result = false;
        for (int[] row : board) {
            int counter2 = 0;
            for (int cell : row) {
                if (player == cell) {
                    for (int i = 0; i > (howMany * (-1)); i--) {
                        if(counter + i >= 0 && counter2 + i >= 0){
                            if (board[counter + i][counter2 + i] == player) {
                                playerCounter++;
                            }
                        }

                    }
                    if(playerCounter == howMany){
                        result = true;
                    }
                    playerCounter = 0;
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

    private boolean checkRight(int player, int howMany) {
        int counter = 0;
        int playerCounter = 0;
        boolean result = false;
        for (int[] row : board) {
            int counter2 = 0;
            for (int cell : row) {
                if (player == cell) {
                    for (int i = 0; i < howMany; i++) {
                        if(counter2 + i < board[0].length){
                            if (board[counter][counter2 + i] == player) {
                                playerCounter++;
                            }
                        }
                    }
                    if(playerCounter == howMany){
                        result = true;
                    }
                    playerCounter = 0;
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

    private boolean checkDownRight(int player, int howMany) {
        int counter = 0;
        int playerCounter = 0;
        boolean result = false;
        for (int[] row : board) {
            int counter2 = 0;
            for (int cell : row) {
                if (player == cell) {
                    for (int i = 0; i < howMany; i++) {
                        if(counter + i < board.length && counter2 + i < board[0].length){
                            if (board[counter + i][counter2 + i] == player) {
                                playerCounter++;
                            }
                        }
                    }
                    if(playerCounter == howMany){
                        result = true;
                    }
                    playerCounter = 0;
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

    private boolean checkDown(int player, int howMany) {
        int counter = 0;
        int playerCounter = 0;
        boolean result = false;
        for (int[] row : board) {
            int counter2 = 0;
            for (int cell : row) {
                if (player == cell) {
                    for (int i = 0; i < howMany; i++) {
                        if(counter + i < board.length){
                            if (board[counter + i][counter2] == player) {
                                playerCounter++;
                            }
                        }
                    }
                    if(playerCounter == howMany){
                        result = true;
                    }
                    playerCounter = 0;
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

    private boolean checkDownLeft(int player, int howMany) {
        int counter = 0;
        int playerCounter = 0;
        boolean result = false;
        for (int[] row : board) {
            int counter2 = 0;
            for (int cell : row) {
                if (player == cell) {
                    for (int i = 0; i > (howMany * (-1)); i--) {
                        if(counter + i * (-1) < board.length && counter2 + i >= 0){
                            if (board[counter + i * (-1)][counter2 + i] == player) {
                                playerCounter++;
                            }
                        }
                    }
                    if(playerCounter == howMany){
                        result = true;
                    }
                    playerCounter = 0;
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

    private boolean checkLeft(int player, int howMany) {
        int counter = 0;
        int playerCounter = 0;
        boolean result = false;
        for (int[] row : board) {
            int counter2 = 0;
            for (int cell : row) {
                if (player == cell) {
                    for (int i = 0; i > (howMany * (-1)); i--) {
                        if(counter2 + i >= 0){
                            if (board[counter][counter2 + i] == player) {
                                playerCounter++;
                            }
                        }
                    }
                    if(playerCounter == howMany){
                        result = true;
                    }
                    playerCounter = 0;
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

    public boolean hasWon(int player, int howMany) {
        if(checkUp(player, howMany)){
            return true;
        } else if (checkUpRight(player, howMany)){
            return true;
        } else if (checkUpLeft(player, howMany)){
            return true;
        } else if (checkRight(player, howMany)){
            return true;
        } else if (checkDownRight(player, howMany)){
            return true;
        } else if (checkDown(player, howMany)){
            return true;
        } else if (checkDownLeft(player, howMany)){
            return true;
        } else if (checkLeft(player, howMany)){
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