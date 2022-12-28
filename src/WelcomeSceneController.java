import Model.Logic.Player;
import Model.Util.UIText;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WelcomeSceneController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final String[] colours = {"Blue","Red","Green","Yellow"};
    @FXML
    private AnchorPane scenePane;
    @FXML
    private TextField namePlayer1;
    @FXML
    private TextField namePlayer2;
    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML
    private ChoiceBox<String> choiceBox2;
    @FXML
    private Label informationLabelWelcomeScene;


    public void switch_to_GridScene(ActionEvent event) throws IOException {
        if (is_valid_name(namePlayer1) && is_valid_name(namePlayer2) &&
                has_not_same_colour() && has_not_same_name()) {
            // create the 2 Players
            Player p1 = new Player(choiceBox1.getValue(), namePlayer1.getText());
            Player p2 = new Player(choiceBox2.getValue(), namePlayer2.getText());

            execute_scene_switch(event, p1, p2);
        } else {
            informationLabelWelcomeScene.setText(UIText.welcomeSceneInformation());
        }
    }
    private boolean is_valid_name(TextField namePlayer) {
        String name = namePlayer.getText();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_][a-zA-Z0-9_. ]*$");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
    private boolean has_not_same_name() {
        return !Objects.equals(namePlayer1.getText(), namePlayer2.getText());
    }
    private boolean has_not_same_colour() {
        return !Objects.equals(choiceBox1.getValue(), choiceBox2.getValue());
    }
    private void execute_scene_switch(ActionEvent event, Player p1, Player p2) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/GridScene.fxml"));
        root = loader.load();

        pass_data(loader, p1, p2);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void pass_data(FXMLLoader loader, Player p1, Player p2) {
        // pass Name and Colour of Players to GridScene
        GridSceneController gsc = loader.getController();

        gsc.pass_name(p1.get_name(), p2.get_name());
        gsc.pass_colour(p1.get_sColour(), p2.get_sColour());

        gsc.display_name1(p1.get_name(), p1.get_sColour());
        gsc.display_name2(p2.get_name(), p2.get_sColour());
    }

    public void exit_program() {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox1.getItems().addAll(colours);
        choiceBox1.getSelectionModel().selectFirst();

        choiceBox2.getItems().addAll(colours);
        choiceBox2.getSelectionModel().selectLast();
    }

}