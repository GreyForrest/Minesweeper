package ch.bbbaden.minesweeper;

import java.util.ArrayList;
import java.util.Scanner;

public class Starter {

    public static void main(String[] args) {
        //Get Height and Width
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> boardVariables = new ArrayList<>();
        boolean correctAnswer = false;
        System.out.println("Bitte geben Sie die Breite des Spielfeldes ein.");
        do {
            String strWidth = scanner.nextLine();
            try {
                int width = Integer.valueOf(strWidth);
                boardVariables.add(width);
                correctAnswer = true;
            } catch (Exception e) {
                System.out.println("Ihre Eingabe scheint ungültig zu sein, versuchen Sie es erneut.");
                correctAnswer = false;
            }
        } while (!correctAnswer);
        correctAnswer = false;
        System.out.println("Bitte geben Sie die Höhe des Spielfeldes ein.");
        do {
            String strHeight = scanner.nextLine();
            try {
                int height = Integer.valueOf(strHeight);
                boardVariables.add(height);
                correctAnswer = true;
            } catch (Exception e) {
                System.out.println("Ihre Eingabe scheint ungültig zu sein, versuchen Sie es erneut.");
                correctAnswer = false;
            }
        } while (!correctAnswer);
        //Create new game
        Board board = new Board(boardVariables.get(0), boardVariables.get(1));
        Game game = new Game(board);
        game.starterAusgabe();
        game.playGameTilFinished();
    }
}
