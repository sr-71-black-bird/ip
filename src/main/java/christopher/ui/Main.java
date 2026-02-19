package christopher.ui;

import java.io.IOException;

import christopher.task.WrongInstructionException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Christopher using FXML.
 */
public class Main extends Application {

    private final Christopher christopher;

    public Main() throws IOException, WrongInstructionException {
        this.christopher = new Christopher();
    }

    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Christopher");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChristopher(christopher); // inject the Christopher instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
