package ch.bbbaden.minesweeper;

import java.util.ArrayList;

public final class NormalField extends Field{

    private final static boolean IS_MINE = false;

    @Override
    public void calculateValue() {
            int minesAroundIt = 0;
            for (Field field : neighbourFields) {
                if(field.getIS_MINE()){
                    minesAroundIt++;
                }
           }super.value = (char)(minesAroundIt + '0');
    }

    @Override
    public boolean getIS_MINE() {
        return IS_MINE;
    }

    public void setNeighbourFields(ArrayList<Field> neighbourFields){
        super.neighbourFields = neighbourFields;
    }
}
