package com.codecool.fiveinarow;

public class FiveInARow {

    public static void main(String[] args) {
        Game game = new Game(11, 11);
        game.enableAi(1, 5);
        game.enableAi(2, 5);
        game.play(5);
    }
}
