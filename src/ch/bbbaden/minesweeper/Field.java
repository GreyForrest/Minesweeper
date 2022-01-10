package ch.bbbaden.minesweeper;

import java.util.ArrayList;

public abstract class Field {

    protected char value;
    protected char cover = '-';
    protected boolean isUncovered = false;
    protected ArrayList<Field> neighbourFields = new ArrayList<>();

    public abstract boolean getIS_MINE();

    //return either cover or value, depending on isUncovered
    public final char getValue() {
        if(isUncovered) {
            return value;
        } else {
            return cover;
        }
    }


    //calculates value with neighbourfields
    public abstract void calculateValue();

    public final void setFlag(){
        this.cover = 'X';
    }

    public final void uncoverField(){
        isUncovered = true;
    }

    public abstract void setNeighbourFields(ArrayList<Field> neighbourFields);
}

