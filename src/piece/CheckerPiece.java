package piece;

import colors.Colors;
import domain.Location;

import java.util.List;

public abstract class CheckerPiece extends Piece {


    private boolean isKing = false;

    public CheckerPiece(int x, int y, Colors colors, boolean isKing) {
        super(x, y, colors);
        this.isKing = isKing;
    }

    public abstract List<Location> possibleMovements();

    protected abstract List<Location> possibleEat();



    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }
}
