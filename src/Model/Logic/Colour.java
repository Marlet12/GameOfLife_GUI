package Model.Logic;

public enum Colour {
    BLUE("B"),
    RED("R"),
    GREEN("G"),
    YELLOW("Y"),
    CELL("X"),
    NONE(" ");

    private final String colour;

    Colour(String symbol) {
        this.colour = symbol;
    }

    public String get_colour() {
        return this.colour;
    }


}
