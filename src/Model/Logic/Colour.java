package Model.Logic;

public enum Colour {
    BLUE("Blue"),
    RED("Red"),
    GREEN("Green"),
    YELLOW("Yellow"),
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
