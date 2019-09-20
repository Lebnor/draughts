package colors;

public enum Colors {

    RED, BLUE;


    Colors() {

    }

    @Override
    public String toString() {

        return this.name().toLowerCase();
    }
}
