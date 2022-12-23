package Model.Cells;

import Model.Logic.Colour;
import Model.Util.Coordinate;

public class Cell {
    private Colour colour;
    private final Coordinate coordinate;


    public Cell(Coordinate a) {
        this.coordinate = a;
        this.colour = Colour.NONE;
    }

    Cell(Coordinate a, Colour colour) {
        this.coordinate = a;
        this.colour = colour;
    }


    public Coordinate get_coordinate() {
        return this.coordinate.get_copy();
    }

    public Colour get_colour() {
        return this.colour;
    }

    void kill() {
        this.colour = Colour.NONE;
    }

    public boolean is_alive() {
        return this.colour != Colour.NONE;
    }
}
