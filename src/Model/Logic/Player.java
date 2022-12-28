package Model.Logic;

public class Player {

    private Colour colour;
    private String name;

    public Player(Colour colour, String name) {
        this.colour = colour;
        this.name = name;
    }

    public Colour get_colour() {
        return this.colour;
    }

    public String get_name() {
        return this.name;
    }
}
