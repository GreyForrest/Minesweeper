package ch.bbbaden.minesweeper;

import java.util.ArrayList;

public final class Mine extends Field{

    private final static boolean IS_MINE = true;
    @Override
    public void calculateValue() {
        super.value = '*';
    }

    @Override
    public void uncoverField() {
        super.isUncovered = true;
    }

    @Override
    public void setNeighbourFields(ArrayList<Field> neighbourFields) {
        super.neighbourFields = neighbourFields;
    }

    @Override
    public boolean getIS_MINE() {
        return IS_MINE;
    }
}
