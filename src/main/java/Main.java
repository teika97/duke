import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke;
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Main() throws DukeException {
        duke = new Duke();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Duke");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            // Prints out welcome message upon start-up
            // Adapted from https://github.com/nus-cs2103-AY1920S1/duke/pull/286/commits/b60e9a198bd89091b947ff187cece2350af56695?file-filters%5B%5D=.gradle&file-filters%5B%5D=.java&file-filters%5B%5D=.jpg&file-filters%5B%5D=.png#diff-c197962302397baf3a4cc36463dce5eaR9
            VBox dialogContainer = (VBox) scene.lookup("#dialogContainer");
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(duke.showWelcome(), dukeImage)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

