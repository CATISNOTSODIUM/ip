package cattis.command;

import java.io.IOException;

import cattis.CattisInterface;
import cattis.Configuration;
import cattis.Constants;
import cattis.Main;
import cattis.exception.CattisException;
import javafx.stage.Stage;

/**
 * Represents opening calendar pop up
 */
public class CalendarCommand extends Command {
    private static final String CALENDAR_SCREEN = "/view/Calendar.fxml";
    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        cattis.getUi().showMessage(Constants.OPEN_CALENDAR);
        try {
            Configuration config = new Configuration(
                    CALENDAR_SCREEN,
                    0.6,
                    0.7 );
            Main.setStage(new Stage(), config);
            assert config.getLoader() != null;

        } catch (IOException e) {
            throw new CattisException(e.getMessage());
        }
    }
}
