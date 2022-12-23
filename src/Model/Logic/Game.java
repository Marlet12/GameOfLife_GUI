package Model.Logic;

import Model.Board.Grid;
import Model.Cells.AliveCells;
import Model.Util.Coordinate;
import Model.Util.Display;

import java.util.ArrayList;

public class Game {
    private static Game instance = null;

    private Player p1;
    private Player p2;
    private AliveCells cells;
    private Grid grid;

    private ArrayList<Colour> colours = new ArrayList<>();

    private Game() {
    }

    ;

    public static Game get_instance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void start() {
        this.setup();
        this.loop();
        this.end();
    }


    private void setup() {
        this.colours = new ArrayList<>();
        this.colours.add(Colour.YELLOW);
        this.colours.add(Colour.GREEN);

        this.p1 = new Player(Colour.YELLOW, "Player_1 (Y)");
        this.p2 = new Player(Colour.GREEN, "Player_2 (G)");
        this.grid = new Grid();
        this.cells = new AliveCells(grid, this.colours);
    }

    private void loop() {
        while (!this.game_over()) {
            inner_loop(p1);

            if (!this.game_over())
                inner_loop(p2);
        }
    }

    private void inner_loop(Player p) {
        Display.turn(p.get_name());
        this.grid.draw();

        Colour temp = p.get_colour();

        Display.kill();
        Coordinate c = Coordinate.input();

        while (!this.cells.can_kill(c, temp)) {
            Display.draw_invalid_kill();
            c = Coordinate.input();
        }

        this.cells.kill(c, temp);
        this.grid.draw();
        Display.create();

        c = Coordinate.input();

        while (!this.cells.can_create(c, temp)) {
            Display.draw_invalid_create();
            c = Coordinate.input();
        }

        this.cells.create(c, temp);

        this.cells.evolve();
    }

    private void end() {
        if (cells.is_alive(p1.get_colour()))
            Display.winner(p1.get_name());

        else
            Display.winner(p2.get_name());

        this.grid.draw();
    }

    private boolean game_over() {
        return !(this.cells.is_alive(p1.get_colour()) && this.cells.is_alive(p2.get_colour()));
    }

}
