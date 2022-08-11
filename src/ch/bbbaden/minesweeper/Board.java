package ch.bbbaden.minesweeper;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public final class Board {

    //Attributes
    private final int board_height;
    private final int board_width;
    private final Field[][] board;
    private final int numberOfMines;
    private Random random = new Random();
    Scanner in = new Scanner(System.in);

    //Concstructor
    public Board(final int board_width, final int board_height) {
        this.board_height = board_height;
        this.board_width = board_width;
        board = new Field[board_height][board_width];
        numberOfMines = board_height*board_width/4;
        System.out.println("In diesem Spiel gibt es " + numberOfMines + " Bombe(n).");
        createBoard();
    }

    //Checking if the game is finished
    public boolean isGameFinished(){
       return (areAllBombsSet()||everythingUncoveredExceptBombs());
    }

    //drawing the board in the console
    public void drawBoard(){
        String strBoard = "";
        for(int i = 0; i < board_height; i++){
            for(int j = 0; j < board_width; j++){
                strBoard += board[i][j].getValue() + " ";
            }
            strBoard += "\n";
        }
        System.out.println(strBoard);
    }

    //uncover fields with coordinates
    public ArrayList<Field> uncoverFieldWithCoordinates(){
        final ArrayList<Field> fieldsUncovered = new ArrayList<>();
        System.out.println("Geben Sie an, welche Felder Sie aufdecken wollen (1,2/2,3):");
        final String strCordsToUncover = in.nextLine().replace(" ", "");
        final String[] koordinaten = strCordsToUncover.split("/");
        int xKoordinate;
        int yKoordinate;
        for (String string : koordinaten) {
            try {
                final String[] einzelneKoordinaten = string.split(",");
                if (einzelneKoordinaten.length == 2) {
                    xKoordinate = Integer.valueOf(einzelneKoordinaten[0]) - 1;
                    yKoordinate = Integer.valueOf(einzelneKoordinaten[1]) - 1;
                } else {
                    throw new Exception();
                }
                if (!(xKoordinate < board_width)) {
                    throw new Exception();
                }
                if (!(yKoordinate < board_height)) {
                    throw new Exception();
                }
                board[yKoordinate][xKoordinate].uncoverField();
                if(board[yKoordinate][xKoordinate].getIS_MINE()){
                    System.out.println("Sie haben eine Bombe aufgedeckt");
                    uncoverAllFields();
                    drawBoard();
                    System.exit(0);
                }
                fieldsUncovered.add(board[yKoordinate][xKoordinate]);
            } catch (Exception e) {
                System.out.println("Ihre Eingabe scheint ungültig zu sein. Es werden keine weiteren Felder aufgedeckt.");
                break;
            }
        }
        return fieldsUncovered;
    }

    //mark a field as a mine by putting an X over it
    public void markFieldAsMine(){
        System.out.println("Möchten Sie Felder als Bombe markieren? j/n");
        final boolean markBombs = in.nextLine().equalsIgnoreCase("J");
        if(markBombs) {
            System.out.println("Geben Sie die Koordianten der Felder ein, die Sie als Bombe markieren wollen: 1,5/2,5");
            final String strCordsMines = in.nextLine().replace(" ", "");
            final String[] koordinaten = strCordsMines.split("/");
            int xKoordinate = 0;
            int yKoordinate = 0;
            for (String string : koordinaten) {
                try {
                    final String[] einzelneKoordinaten = string.split(",");
                    if (einzelneKoordinaten.length == 2) {
                        xKoordinate = Integer.valueOf(einzelneKoordinaten[0]) - 1;
                        yKoordinate = Integer.valueOf(einzelneKoordinaten[1]) - 1;
                    } else {
                        throw new Exception();
                    }
                    if (!(xKoordinate <= board_width)) {
                        throw new Exception();
                    }
                    if (!(yKoordinate <= board_height)) {
                        throw new Exception();
                    }
                    board[yKoordinate][xKoordinate].setFlag();
                } catch (Exception e) {
                    System.out.println("Ihre Eingabe scheint ungültig zu sein. Es werden keine weiteren Bomben markiert.");
                    break;
                }
            }
            drawBoard();
        }

    }

    //For isGameFinished
    private boolean areAllBombsSet(){
        int numberFlags = 0;
        for(int i = 0; i < board_height; i++){
            for(int j = 0; j < board_width; j++){
                if(board[i][j].getValue() == 'X'){
                    numberFlags++;
                }
            }
        }
        return numberFlags == numberOfMines;
    }

    //For isGameFinished
    private boolean everythingUncoveredExceptBombs(){
        int numberUncoveredOrFlags = 0;
        for(int i = 0; i < board_height; i++){
            for(int j = 0; j < board_width; j++){
                if(board[i][j].getValue() == 'X' || board[i][j].getValue() == '-'){
                    numberUncoveredOrFlags++;
                }
            }
        }
        return numberUncoveredOrFlags == numberOfMines;
    }

    //Filling board with fields
    private void createBoard(){
        int x;
        int y;
        for(int i = 1; i <= numberOfMines; i++){
            boolean mineSuccessfullPlanted = false;
            do {
                x = random.nextInt(board_width);
                y = random.nextInt(board_height);
                if (board[y][x] == null) {
                    board[y][x] = new Mine();
                    mineSuccessfullPlanted = true;
                }
            }while(!mineSuccessfullPlanted);
        }
        for(int i = 0; i < board_height; i++){
            for(int j = 0; j < board_width; j++){
                if(board[i][j] == null) {
                    board[i][j] = new NormalField();
                }
            }
        }
        for(int i = 0; i < board_height; i++){
            for(int j = 0; j < board_width; j++){
                if(!board[i][j].getIS_MINE())
                board[i][j].setNeighbourFields(getNeighbourFields(i,j));
            }
        }
        calculateAllValues();
    }

    //Return neighbourfields for a certain field
    private ArrayList<Field> getNeighbourFields(final int i, final int j){

        ArrayList<Field> neighbours = new ArrayList<>();
        try {
            neighbours.add(board[i - 1][j]);
        } catch (Exception e) {
        }
        try {
            neighbours.add(board[i + 1][j]);
        } catch (Exception e) {
        }
        try {
            neighbours.add(board[i][j + 1]);
        } catch (Exception e) {
        }
        try {
            neighbours.add(board[i][j - 1]);
        } catch (Exception e) {
        }
        try {
            neighbours.add(board[i - 1][j - 1]);
        } catch (Exception e) {
        }
        try {
            neighbours.add(board[i + 1][j - 1]);
        } catch (Exception e) {
        }
        try {
            neighbours.add(board[i + 1][j + 1]);
        } catch (Exception e) {
        }
        try {
            neighbours.add(board[i - 1][j + 1]);
        } catch (Exception e) {
        }
        return neighbours;
    }

    //For createBoard
    private void calculateAllValues(){
        for(int i = 0; i < board_height; i++) {
            for (int j = 0; j < board_width; j++) {
                board[i][j].calculateValue();
            }
        }
    }

    //for uncoverFieldWithCoordinates
    private void uncoverAllFields(){
        for(int i = 0; i < board_height; i++){
            for(int j = 0; j < board_width; j++){
                board[i][j].uncoverField();
            }
        }
    }
}

