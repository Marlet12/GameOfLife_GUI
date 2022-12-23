import Model.Logic.Colour;
import Model.Logic.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneController {
    private Stage stage;
    private Scene scene;

    private boolean toggleColor = false;

    @FXML
    private Button exitButton;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private TextField namePlayer1;
    @FXML
    private Label labelp1;
    @FXML
    private ChoiceBox<String> choiceBoxColour1;
    @FXML
    private TextField namePlayer2;
    @FXML
    private Label labelp2;
    @FXML
    private ChoiceBox<String> choiceBoxColour2;

    // !!!IMPORTANT!!
    // This method is necessary for the choice boxes but currently makes it impossible to switch to the GridScene
    // So comment or uncomment this method depending on whether you want the Welcome or the Grid scene to work...
    @FXML
    private void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList("Blue", "Red", "Green", "Yellow");
        choiceBoxColour1.setItems(items);
        choiceBoxColour2.setItems(items);
    }


    public void switchToSceneGridPaneButtons(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scenes/GridScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scenes/WelcomeScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setNamePlayer1(ActionEvent event) {
        if (namePlayer1.getText() != null && !namePlayer1.getText().isEmpty() && choiceBoxColour1.getValue() != null) {
            Colour colourp1 = Colour.NONE;
            if (Objects.equals(choiceBoxColour1.getValue(), "Blue")) colourp1 = Colour.BLUE;
            if (Objects.equals(choiceBoxColour1.getValue(), "Red")) colourp1 = Colour.RED;
            if (Objects.equals(choiceBoxColour1.getValue(), "Green")) colourp1 = Colour.GREEN;
            if (Objects.equals(choiceBoxColour1.getValue(), "Yellow")) colourp1 = Colour.YELLOW;

            Player p1 = new Player(colourp1, namePlayer1.getText());
            labelp1.setText("Welcome " + p1.get_name() + "! You have chosen the colour "+ p1.get_colour());
        } else labelp1.setText("You have not entered a name or colour");
    }
    public void setNamePlayer2(ActionEvent event) {
        if (namePlayer2.getText() != null && !namePlayer2.getText().isEmpty() && choiceBoxColour2.getValue() != null) {
            Colour colourp2 = Colour.NONE;
            if (Objects.equals(choiceBoxColour2.getValue(), "Blue")) colourp2 = Colour.BLUE;
            if (Objects.equals(choiceBoxColour2.getValue(), "Red")) colourp2 = Colour.RED;
            if (Objects.equals(choiceBoxColour2.getValue(), "Green")) colourp2 = Colour.GREEN;
            if (Objects.equals(choiceBoxColour2.getValue(), "Yellow")) colourp2 = Colour.YELLOW;

            Player p2 = new Player(colourp2, namePlayer2.getText());
            labelp2.setText("Welcome " + p2.get_name() + "! You have chosen the colour "+ p2.get_colour());
        } else labelp2.setText("You have not entered a name or colour");
    }

    public void gridPaneButtonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (toggleColor) {
            button.setStyle("-fx-background-color: red;");
        } else {
            button.setStyle("-fx-background-color: blue;");
        }
        toggleColor = !toggleColor;
    }

    public void exit_program(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Program");
        alert.setHeaderText("You're about to exit the program.");
        alert.setContentText("Are you sure?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
            System.out.println("-- Exit program");
        }
    }


}