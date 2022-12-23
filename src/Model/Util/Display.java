package Model.Util;

public class Display {
    private static final String letters = "  0 1 2 3 4 5 6 7 8 9";
    private static final String border = " +-+-+-+-+-+-+-+-+-+-+";
    private static final String endline = "=======================";
    private static final String separator = "-----------------------";

    public static void draw_grid(String[][] grid) {
        System.out.println(endline);
        System.out.println(letters);
        System.out.println(border);

        for (int i = 0; i < grid.length; i++) {
            System.out.print(i);
            System.out.print("|");
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j]);
                System.out.print("|");
            }
            System.out.print(i);
            System.out.println("");
        }

        System.out.println(border);
        System.out.println(letters);
        System.out.println(endline);
    }

    public static void draw_invalid_coordinate_input() {
        // message for an invalid coordinate (like "A$")
        System.out.println("Invalid format of coordinate input.");
        System.out.println("Format is: x,y  (vertical,horizontal)");
    }


    public static void draw_invalid_kill() {
        // message for an invalid coordinate (like "A$")
        System.out.println("Invalid Coordinate to kill a cell.");
    }

    public static void draw_invalid_create() {
        // message for an invalid coordinate (like "A$")
        System.out.println("Invalid Coordinate to create a cell.");
    }

    public static void winner(String player) {
        System.out.println(player + " has won.");
    }

    public static void turn(String player) {
        System.out.println(player + ": it is your turn!");
    }

    public static void kill() {
        System.out.println("Enter Coordinate to kill");
    }

    public static void create() {
        System.out.println("Enter Coordinate to place a new Cell");
    }


}
