package cattis;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Represent the configuration of the app
 */
public class Configuration {
    private final String resource;
    private final double horizontalRatio;
    private final double verticalRatio;
    private FXMLLoader loader = null;
    private Scene scene = null;
    /**
     * Set up the configuration
     * @param resource the FXML file path
     */
    public Configuration(String resource) {
        this.resource = resource;
        this.horizontalRatio = 0.5;
        this.verticalRatio = 0.5;
    }

    /**
     * Manual configuration
     * @param resource resource file
     * @param horizontalRatio target ratio reference to screen size
     * @param verticalRatio target ratio reference to screen size
     */
    public Configuration(String resource, double horizontalRatio,
                         double verticalRatio) {
        this.resource = resource;
        this.horizontalRatio = horizontalRatio;
        this.verticalRatio = verticalRatio;
    }

    private Scene getScene() throws IOException {
        if (this.loader == null) {
            this.loader = new FXMLLoader(getClass().getResource(resource));
        }
        if (this.scene == null) {
            this.scene = new Scene(this.loader.load());
        }
        return this.scene;
    }

    /**
     * Set stage with configuration
     * @param stage the target stage
     * @throws IOException cannot retrieve scene
     */
    public void loadConfiguration(Stage stage) throws IOException {
        // Set screen position
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setWidth(screenBounds.getWidth() * this.horizontalRatio);
        stage.setHeight(screenBounds.getHeight() * this.verticalRatio);

        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
        stage.setScene(this.getScene());
    }
}
