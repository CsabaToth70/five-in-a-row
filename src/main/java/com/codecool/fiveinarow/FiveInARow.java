package com.codecool.fiveinarow;

public class FiveInARow {

    public static void main(String[] args) {
        Game game = new Game(6, 6);
        game.enableAi(1);
        game.enableAi(2);
        game.play(5);
    }
}
