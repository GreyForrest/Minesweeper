package ch.bbbaden.minesweeper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public final class Game {

    //Attributes
    private Board board;

    //Constructor
    public Game(Board board) {
        this.board = board;
    }

    //drawing the field for the first time
    public void starterAusgabe(){
        board.drawBoard();
    }

    //repeating uncoverField and setFlags until the game is finished
    public void playGameTilFinished(){
        do{
            ArrayList<Field> uncoveredFields = uncoverField();
            int notZeroCounter = 0;
            for(Field field : uncoveredFields){
                if(field.getValue() != '0'){
                  notZeroCounter++;
                }
            }
            if(board.isGameFinished()){
                System.out.println("Bravo, Sie haben das Spiel fertig gespielt! Gut gemacht!");
                System.exit(0);
            }
            if(notZeroCounter > 0) { setFlags(); }
            if(board.isGameFinished()){
                System.out.println("Bravo, Sie haben das Spiel fertig gespielt! Gut gemacht!");
                System.exit(0);
            }
        } while(true);
    }

    //for playGameTilFinished
    private ArrayList<Field> uncoverField(){
        ArrayList<Field> uncoveredFields = board.uncoverFieldWithCoordinates();
        board.drawBoard();
        return uncoveredFields;
    }

    //for playGameTilFinished
    private void setFlags(){
        board.markFieldAsMine();
    }
}
