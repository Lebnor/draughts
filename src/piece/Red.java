package piece;

import colors.Colors;
import domain.Location;
import teams.TeamRed;

import java.util.ArrayList;
import java.util.List;

public class Red extends TeamRed {


    public Red(int x, int y, Colors colors, boolean isKing) {
        super(x, y, colors, isKing);
    }

    @Override
    public List<Location> possibleMovements() {
        List<Location> possibleMovements = new ArrayList<>();

        possibleMovements.add(new Location(this.getLocation().getX() - 1, this.getLocation().getY() - 1));
        possibleMovements.add(new Location(this.getLocation().getX() + 1, this.getLocation().getY() - 1));

        if (isKing()) {
            CheckerPiece cmp = new Blue(this.getLocation().getX(), this.getLocation().getY(), Colors.BLUE, false);
            possibleMovements.addAll(((Blue) cmp).possibleMovements());
        }

        return possibleMovements;
    }

    @Override
    protected List<Location> possibleEat() {
        return null;
    }
}
