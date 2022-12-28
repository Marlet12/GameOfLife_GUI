import Model.Util.UIText;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GridSceneController {
    private String colourPlayer1;
    private String colourPlayer2;
    private String namePlayer1;
    private String namePlayer2;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean toggleColor = false;
    @FXML
    private Label nameLabel1;
    @FXML
    private Label nameLabel2;
    @FXML
    private Label informationLabelGridScene;

    public void display_name1(String name, String colour) {
        nameLabel1.setText(UIText.gridScenePlayerAndColour(name, colour));
    }
    public void display_name2(String name, String colour) {
        nameLabel2.setText(UIText.gridScenePlayerAndColour(name, colour));
    }
    public void pass_colour(String colour1, String colour2) {
        colourPlayer1 = colour1;
        colourPlayer2 = colour2;
    }
    public void pass_name(String name1, String name2) {
        namePlayer1 = name1;
        namePlayer2 = name2;
    }

    public void cellButton_clicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (toggleColor) {
            button.setStyle("-fx-background-color: " + colourPlayer2 + ";");
            informationLabelGridScene.setText(UIText.gridSceneInformationLabel(namePlayer1));
        } else {
            button.setStyle("-fx-background-color: " + colourPlayer1 + ";");
            informationLabelGridScene.setText(UIText.gridSceneInformationLabel(namePlayer2));
        }
        toggleColor = !toggleColor;
    }

    public void start_game() {
        // TODO: implement cell killing rules
        System.out.println("START pressed");
    }
    public void switch_to_WelcomeScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/WelcomeScene.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
