package Model.Util;

import java.util.Scanner;

public class Input {
    private static Scanner user_input = new Scanner(System.in);

    static String get_user_input_single() {
        // gets a user input of a single coordinate like A0
        String xy = user_input.nextLine();
        return xy;
    }

    static String[] get_user_input_multiple() {
        // gets a user input of multiple coordinates (not necessarily two) like A0,A1,A2
        String xy = user_input.nextLine();

        if (xy.endsWith(","))
            xy += " ";

        return xy.split(",");
    }
}
