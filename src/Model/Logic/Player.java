package Model.Logic;

public class Player {

    private Colour colour;
    private String sColour;
    private String name;

    public Player(Colour colour, String name) {
        this.colour = colour;
        this.name = name;
    }
    public Player(String colour, String name) {
        this.sColour = colour;
        this.name = name;
    }

    public Colour get_colour() {
        return this.colour;
    }
    public String get_sColour() {
        return this.sColour;
    }

    public String get_name() {
        return this.name;
    }
}
