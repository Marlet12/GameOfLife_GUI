package Model.Board;

import Model.Cells.Cell;
import Model.Util.Coordinate;
import Model.Util.Display;

public class Grid {
    private final int grid_size_x = Coordinate.get_max_x() + 1;
    private final int grid_size_y = Coordinate.get_max_y() + 1;
    private Square[][] grid;


    public Grid() {

        this.grid = new Square[grid_size_x][grid_size_y];

        for (int i = 0; i < grid_size_x; i++) {
            for (int j = 0; j < grid_size_y; j++) {
                this.grid[i][j] = new Square(new Coordinate(i, j));

            }
        }
    }

    public String[][] get_grid() {
        String[][] draw_grid = new String[grid_size_x][grid_size_y];

        for (int i = 0; i < grid_size_x; i++) {
            for (int j = 0; j < grid_size_y; j++) {
                draw_grid[i][j] = this.grid[i][j].get_colour().get_colour();
            }

        }
        return draw_grid;
    }

    public void kill(Coordinate a) {
        this.grid[a.get_x()][a.get_y()].kill();
    }

    public void create(Coordinate a, Cell cell) {
        this.grid[a.get_x()][a.get_y()].create(cell);
    }

    public void draw() {
        Display.draw_grid(this.get_grid());
    }
}
