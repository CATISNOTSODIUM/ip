package cattis;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main instance of the application
 */
public class Main extends Application {

    private Cattis cattis = new Cattis();

    @Override
    public void start(Stage stage) {
        try {
            var startConfig = new Configuration(
                    "/view/ChatScreen.fxml", 0.3, 0.7);
            setStage(stage, startConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setStage(Stage stage, Configuration config) throws IOException {
        config.loadConfiguration(stage);
        stage.show();
    }
}
