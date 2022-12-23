package Model.Util;

import java.util.ArrayList;
import java.util.Random;

//the Coordinate class handles all the calculations and interactions with coordinates
public class Coordinate {
    private static final int min_x = 0;
    private static int max_x = 9;
    private static final int min_y = 0;
    private static int max_y = 9;
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        // note Coordinates are constructed in the format a = new Coordinate(y,x)
        // with y as the horizontal component and x as the vertical component
        assert (is_valid_coordinate(x, y));

        this.x = x;
        this.y = y;
    }


    // the following methods implement getter methods for the private instance variables.
    public int get_x() {
        return this.x;
    }

    public int get_y() {
        return this.y;
    }

    public static int get_min_x() {
        return min_x;
    }

    public static int get_min_y() {
        return min_y;
    }

    public static int get_max_x() {
        return max_x;
    }

    public static int get_max_y() {
        return max_y;
    }

    public Coordinate get_copy() {
        return new Coordinate(this.x, this.y);
    }

    public static boolean is_valid_coordinate(int x, int y) {
        // validates whether a potential coordinate is valid or not
        return (min_x <= x && max_x >= x) && (min_y <= y && max_y >= y);
    }


    // the following methods implement simple boolean comparisons for coordinates
    // note: "equal" and "equals" (last method in the class) are two different methods with varying functionality and implementation
    public static boolean is_in_line(Coordinate a, Coordinate b) {
        return equal_x(a, b) || equal_y(a, b);
    }

    public static boolean equal_x(Coordinate a, Coordinate b) {
        return a.x == b.x;
    }

    public static boolean equal_y(Coordinate a, Coordinate b) {
        return a.y == b.y;
    }

    public static boolean equal(Coordinate a, Coordinate b) {
        return equal_x(a, b) && equal_y(a, b);
    }

    public static boolean smaller_x(Coordinate a, Coordinate b) {
        return a.x < b.x;
    }

    public static boolean smaller_y(Coordinate a, Coordinate b) {
        return a.y < b.y;
    }

    public static int distance(Coordinate a, Coordinate b) {
        // calculates the horizontal or vertical distance between two coordinates (but not diagonally!)
        // Example: Distance from A0 to A4 is 3 (and not 4). Distance from A0 to C1 does not exist.
        assert is_in_line(a, b);

        if (equal_y(a, b))
            return Math.abs(a.x - b.x);
        else
            return Math.abs(a.y - b.y);
    }

    // the following four boolean methods evaluate whether a certain coordinate can be incremented in a direction n-times or not
    // Example: A0 cannot be incremented up at all (A-1 is not a valid coordinate). A0 incremented down by 1 would become A1.
    // Example: B4 incremented to the right by 3 would become E4
    private boolean can_increment_up_by(int n) {
        return is_valid_coordinate(this.x - n, this.y);
    }

    private boolean can_increment_down_by(int n) {
        return is_valid_coordinate(this.x + n, this.y);
    }

    private boolean can_increment_left_by(int n) {
        return is_valid_coordinate(this.x, this.y - n);
    }

    private boolean can_increment_right_by(int n) {
        return is_valid_coordinate(this.x, this.y + n);
    }

    private boolean can_increment_up_right_by(int n) {
        return can_increment_right_by(n) && can_increment_up_by(n);
    }

    private boolean can_increment_up_left_by(int n) {
        return can_increment_left_by(n) && can_increment_up_by(n);
    }

    private boolean can_increment_down_left_by(int n) {
        return can_increment_down_by(n) && can_increment_left_by(n);
    }

    private boolean can_increment_down_right_by(int n) {
        return can_increment_down_by(n) && can_increment_right_by(n);
    }

    // the following four methods return a new Coordinated that is incremented n-times in the corresponding direction
    // example: A0 incremented 4 times to the right becomes E0.
    private Coordinate increment_up_by(int n) {
        return new Coordinate(this.x - n, this.y);
    }

    private Coordinate increment_down_by(int n) {
        return new Coordinate(this.x + n, this.y);
    }

    private Coordinate increment_left_by(int n) {
        return new Coordinate(this.x, this.y - n);
    }

    private Coordinate increment_right_by(int n) {
        return new Coordinate(this.x, this.y + n);
    }

    private Coordinate increment_up_right_by(int n) {
        return new Coordinate(this.x - n, this.y + n);
    }

    private Coordinate increment_up_left_by(int n) {
        return new Coordinate(this.x - n, this.y - n);
    }

    private Coordinate increment_down_left_by(int n) {
        return new Coordinate(this.x + n, this.y - n);
    }

    private Coordinate increment_down_right_by(int n) {
        return new Coordinate(this.x + n, this.y + n);
    }

    public ArrayList<Coordinate> with_distance_cross(int d) {
        // returns all Coordinates at distance d in a list.
        // Example: all points at distance 2 from A0 are: C0 and A2
        // Note: the list can be empty. Example: all points at distance 11 from A0 in a 10x10 grid (there are none)
        assert (d > 0);

        ArrayList<Coordinate> points = new ArrayList<>();

        if (this.can_increment_up_by(d))
            points.add(this.increment_up_by(d));

        if (this.can_increment_down_by(d))
            points.add(this.increment_down_by(d));

        if (this.can_increment_left_by(d))
            points.add(this.increment_left_by(d));

        if (this.can_increment_right_by(d))
            points.add(this.increment_right_by(d));

        return points;
    }

    public ArrayList<Coordinate> with_distance_corner(int d) {
        // returns all Coordinates at distance d in a list.
        // Example: all points at distance 2 from A0 are: C0 and A2
        // Note: the list can be empty. Example: all points at distance 11 from A0 in a 10x10 grid (there are none)
        assert (d > 0);

        ArrayList<Coordinate> points = new ArrayList<>();

        if (this.can_increment_up_right_by(d))
            points.add(this.increment_up_right_by(d));

        if (this.can_increment_up_left_by(d))
            points.add(this.increment_up_left_by(d));

        if (this.can_increment_down_left_by(d))
            points.add(this.increment_down_left_by(d));

        if (this.can_increment_down_right_by(d))
            points.add(this.increment_down_right_by(d));

        return points;
    }


    public ArrayList<Coordinate> with_distance(int d) {
        // returns all Coordinates at distance d in a list.
        // Example: all points at distance 2 from A0 are: C0 and A2
        // Note: the list can be empty. Example: all points at distance 11 from A0 in a 10x10 grid (there are none)
        assert (d > 0);

        ArrayList<Coordinate> points = new ArrayList<>();
        points.addAll(this.with_distance_cross(d));
        points.addAll(this.with_distance_corner(d));

        return points;
    }


    // the following two methods build a range of coordinates in the corresponding direction (x or y)
    // Example: The range from A0 to A4 is the list {A0,A1,A2,A3,A4}
    // Example The range from A4 to A0 is also the list {A0,A1,A2,A3,A4} because the first element is always the smallest
    private static ArrayList<Coordinate> build_range_x(Coordinate a, Coordinate b) {
        //assert equal_y(a,b);

        ArrayList<Coordinate> range = new ArrayList<>();
        range.add(a);

        for (int i = a.x + 1; i < b.x; i++) {
            Coordinate c = new Coordinate(i, a.get_y());
            range.add(c);
        }
        range.add(b);
        return range;
    }

    private static ArrayList<Coordinate> build_range_y(Coordinate a, Coordinate b) {
        //assert equal_x(a,b);

        ArrayList<Coordinate> range = new ArrayList<>();
        range.add(a);

        for (int i = a.y + 1; i < b.y; i++) {
            Coordinate c = new Coordinate(a.get_x(), i);
            range.add(c);
        }
        range.add(b);
        return range;
    }

    public static ArrayList<Coordinate> get_range(Coordinate a, Coordinate b) {
        // this method builds the correct range for two given coordinates
        // Example: the range from A0 to A4 is the list {A0,A1,A2,A3,A4}
        // in case of a <=> b the list containing only Coordinate a is returned
        // the coordinates a and b must be on the same x- or y-axis
        assert is_in_line(a, b);

        if (equal(a, b)) {
            ArrayList<Coordinate> range = new ArrayList<>();
            range.add(a);
            return range;
        } else if (equal_x(a, b)) {
            if (smaller_y(a, b)) {
                return build_range_y(a, b);
            } else {
                return build_range_y(b, a);
            }
        } else {
            if (smaller_x(a, b)) {
                return build_range_x(a, b);
            } else {
                return build_range_x(b, a);
            }
        }
    }

    public static Coordinate random_coordinate(ArrayList<Coordinate> list) {
        // returns a random Coordinate from a non-empty list of Coordinates
        assert list.size() > 0;

        Random rnd = new Random();
        int randomNum = rnd.nextInt(list.size());
        return list.get(randomNum);
    }


    @Override
    public boolean equals(Object o) {
        // overrides the default equals method in Object
        if (o == this)
            return true;
        else if (!(o instanceof Coordinate))
            return false;
        else
            return equal(this, (Coordinate) o);
    }

    @Override
    public int hashCode() {
        int a = this.x;
        int b = this.y;
        return (a + b) * (a + b + 1) / 2 + a;
    }


    public static Coordinate input() {
        String[] s = Input.get_user_input_multiple();

        if (s.length != 2) {
            Display.draw_invalid_coordinate_input();
            return Coordinate.input();
        } else {
            try {
                int x = Integer.parseInt(s[0]);
                int y = Integer.parseInt(s[1]);

                if (is_valid_coordinate(x, y))
                    return new Coordinate(x, y);
                else {
                    Display.draw_invalid_coordinate_input();
                    return Coordinate.input();
                }
            } catch (Exception e) {
                Display.draw_invalid_coordinate_input();
                return Coordinate.input();
            }
        }

    }


}



