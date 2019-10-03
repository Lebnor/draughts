package piece;

import colors.Colors;
import domain.Location;
import teams.TeamBlue;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Blue extends  CheckerPiece implements TeamBlue {


    public Blue(int x, int y, Colors colors, boolean isKing) {
        super(x, y, colors, isKing);
    }

    @Override
    public List<Location> oneStepForward() {
        List<Location> possibleMoves = new ArrayList<>();

        possibleMoves.add(new Location(this.getLocation().getX() + 1, this.getLocation().getY() + 1));
        possibleMoves.add(new Location(this.getLocation().getX() - 1, this.getLocation().getY() + 1));

        if (isKing()) {
            CheckerPiece compare = new Red(this.getLocation().getX(),this.getLocation().getY(), Colors.RED,false);
            possibleMoves.addAll(((Red) compare).oneStepForward());
        }

        ListIterator iterator = possibleMoves.listIterator();
        while (iterator.hasNext()){
            Location loc = (Location) iterator.next();
            if (loc.getX() == this.getLocation().getX() || loc.getY() == this.getLocation().getY()){
                iterator.remove();
            }
        }
        return possibleMoves;
    }

    @Override
    public List<Location> possibleEat() {
        return null;
    }

}
