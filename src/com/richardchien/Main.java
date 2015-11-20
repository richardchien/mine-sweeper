package com.richardchien;

import java.util.Scanner;

public class Main {

    private static Scanner console = new Scanner(System.in);

    private static MineSweeperGame game;

    public static void main(String[] args) {
        game = new MineSweeperGame(9, 9, 10);
        game.startGame();
        printMap();

        while (game.getState() == MineSweeperGame.GameState.Playing
                || game.getState() == MineSweeperGame.GameState.Waiting) {
            String line = console.nextLine();
            String[] cmd = line.split(" ");
            int row = Integer.parseInt(cmd[1]);
            int col = Integer.parseInt(cmd[2]);
            switch (cmd[0]) {
                case "DIG":
                    game.digAt(row, col);
                    break;
                case "FLAG":
                    game.flagAt(row, col);
                    break;
                case "UNFLAG":
                    game.unflagAt(row, col);
                    break;
                default:
                    System.out.println("Unknown command");
            }
            printMap();
        }

        if (game.getState() == MineSweeperGame.GameState.Win) {
            System.out.println("You Win");
        } else {
            System.out.println("You Lose");
        }
    }

    private static void printMap(){
        int[][] map = game.getMapToDisplay();
        System.out.println("  0 1 2 3 4 5 6 7 8");
        System.out.println(" -------------------");
        for (int i = 0; i < 9; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < 9; j++) {
                if (map[i][j] == MineSweeperGame.kMapUnshown) {
                    System.out.print("*|");
                } else if (map[i][j] == MineSweeperGame.kMapFlaged) {
                    System.out.print("F|");
                } else if (map[i][j] == MineSweeperGame.kMapBomb) {
                    System.out.print("X|");
                } else {
                    System.out.print(map[i][j] + "|");
                }
            }
            System.out.print("\n");
        }
        System.out.println(" -------------------");
    }
}
