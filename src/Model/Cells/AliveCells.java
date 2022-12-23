package Model.Cells;

import Model.Board.Grid;
import Model.Logic.Colour;
import Model.Util.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AliveCells {
    private final Grid grid;
    private final ArrayList<Colour> colours;
    private final HashMap<Coordinate, Cell> cells;

    public AliveCells(Grid grid, ArrayList<Colour> colours) {
        this.grid = grid;
        this.colours = colours;
        this.cells = new HashMap<>();

        this.setup();
    }

    private void setup() {
        assert (this.colours.size() == 2);

        this.create(new Coordinate(1, 4), this.colours.get(0));
        this.create(new Coordinate(1, 5), this.colours.get(0));
        this.create(new Coordinate(2, 4), this.colours.get(0));
        this.create(new Coordinate(2, 5), this.colours.get(0));

        this.create(new Coordinate(7, 4), this.colours.get(1));
        this.create(new Coordinate(7, 5), this.colours.get(1));
        this.create(new Coordinate(8, 4), this.colours.get(1));
        this.create(new Coordinate(8, 5), this.colours.get(1));

    }

    public void create(Coordinate a, Colour c) {
        assert this.can_create(a, c);

        this.cells.put(a, new Cell(a, c));
        this.grid.create(a, this.cells.get(a));

    }

    public void kill(Coordinate a, Colour c) {
        assert this.can_kill(a, c);

        this.cells.get(a).kill();
        this.cells.remove(a);
        this.grid.kill(a);
    }

    public void evolve() {
        HashSet<Coordinate> coordinates = this.find();
        HashMap<Coordinate, Colour> changes = this.calculate(coordinates);
        this.execute(changes);
    }

    private HashSet<Coordinate> find() {
        HashSet<Coordinate> potential_coordinates = new HashSet<>();

        for (Coordinate c : this.cells.keySet()) {
            potential_coordinates.addAll(c.with_distance(1));
            potential_coordinates.add(c);
        }
        return potential_coordinates;
    }

    private HashMap<Coordinate, Colour> calculate(HashSet<Coordinate> coordinates) {
        HashMap<Coordinate, Colour> changes = new HashMap<>();

        for (Coordinate a1 : coordinates) {
            HashMap<Colour, Integer> neighbours = new HashMap<>();

            for (Colour colour : Colour.values()) {
                neighbours.put(colour, 0);
            }

            ArrayList<Coordinate> neighbour_coordinates = a1.with_distance(1);

            for (Coordinate a2 : neighbour_coordinates) {
                if (this.cells.containsKey(a2)) {
                    Colour temp_colour = this.cells.get(a2).get_colour();
                    int temp_count = neighbours.get(temp_colour);
                    neighbours.put(temp_colour, temp_count + 1);
                }
            }

            neighbours.remove(Colour.NONE);

            if (this.cells.containsKey(a1)) {
                Colour this_colour = this.cells.get(a1).get_colour();

                if ((neighbours.get(this_colour) < 2 || neighbours.get(this_colour) > 3))
                    changes.put(a1, Colour.NONE);
            } else if (neighbours.values().stream().reduce(0, Integer::sum) == 3) {
                for (Colour colour : neighbours.keySet()) {
                    if (neighbours.get(colour) >= 2)
                        changes.put(a1, colour);
                }
            }

        }
        return changes;
    }

    private void execute(HashMap<Coordinate, Colour> changes) {
        for (Coordinate a : changes.keySet()) {
            this.change(a, changes.get(a));
        }
    }

    private void change(Coordinate a, Colour c) {
        if (c == Colour.NONE && this.can_kill(a, c)) {
            this.kill(a, c);
        } else if (c != Colour.NONE && this.can_create(a, c)) {
            this.create(a, c);
        } else if (c != Colour.NONE && this.can_kill(a, c)) {
            this.kill(a, c);
            this.create(a, c);
        }
    }

    public boolean can_create(Coordinate a, Colour c) {
        return this.colours.contains(c) && !this.cells.containsKey(a);
    }

    public boolean can_kill(Coordinate a, Colour c) {
        if (!this.cells.containsKey(a))
            return false;

        else if (this.cells.get(a).get_colour() == c)
            return false;
        else
            return this.cells.get(a).is_alive();
    }


    public boolean is_alive(Colour colour) {
        for (Coordinate a : this.cells.keySet()) {
            if (this.cells.get(a).get_colour() == colour)
                return true;
        }
        return false;
    }

}
