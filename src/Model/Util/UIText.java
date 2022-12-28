package Model.Util;

public class UIText {
    public static String welcomeSceneInformation() {
        return """
                    Please enter a name in the correct format for both players
                    (Name can't begin with a whitespace).
                    (Both players must have different names and colours.)
                    """;
    }
    public static String gridScenePlayerAndColour(String name, String colour) {
        return "Hello " + name + ", your colour is: " + colour;
    }
    public static String gridSceneInformationLabel(String name) {
        return "It is " + name + "'s turn to place a cell.";
    }
}
