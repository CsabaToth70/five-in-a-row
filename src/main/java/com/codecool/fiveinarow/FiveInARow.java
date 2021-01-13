package com.codecool.fiveinarow;

public class FiveInARow {

    public static void main(String[] args) {
        Game game = new Game(4, 4);
        //game.enableAi(1, 4);
        //game.enableAi(2, 4);
        game.play(4);
    }
}
