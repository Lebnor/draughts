package teams;

import colors.Colors;
import domain.Location;
import piece.CheckerPiece;

import java.util.List;

public abstract class TeamRed extends CheckerPiece {


    public TeamRed(int x, int y, Colors colors, boolean isKing) {
        super(x, y, colors, isKing);
    }

    @Override
    public List<Location> possibleMovements() {
        return null;
    }

    @Override
    protected List<Location> possibleEat() {
        return null;
    }
}
