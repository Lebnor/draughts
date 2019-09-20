package piece;

import colors.Colors;
import domain.Location;

import java.util.Objects;

public abstract class Piece {

    private Location location;
    private Colors color;


    public Piece(int x, int y, Colors color) {
        this.location = new Location(x,y);
        this.color = color;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return location.equals(piece.location) &&
                color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, color);
    }

    @Override
    public String toString() {
        return color + " piece (" + getLocation().getX() + "," + getLocation().getY()+ ")";
    }
}
