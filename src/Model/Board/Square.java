package Model.Board;

import Model.Cells.Cell;
import Model.Logic.Colour;
import Model.Util.Coordinate;

class Square {
    private final Coordinate coordinate;
    private Cell cell;

    Square(Coordinate a) {
        this.coordinate = a;
        this.cell = new Cell(this.coordinate);
    }

    void create(Cell cell) {
        if (!this.cell.is_alive())
            this.cell = cell;
    }

    void kill() {
        this.cell = new Cell(this.coordinate);
    }

    Colour get_colour() {
        return this.cell.get_colour();
    }

    boolean is_empty() {
        return !this.cell.is_alive();
    }
}
