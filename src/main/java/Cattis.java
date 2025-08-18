import Exceptions.CattisException;
import Task.*;
import java.util.*;

public class Cattis {
    private final Ui ui;
    private final Parser parser;
    private TaskList taskList;
    public static void main(String[] args) {
        Cattis cattis = new Cattis();
        cattis.run();
    }

    public Cattis() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.taskList = new TaskList();
    }

    public Cattis(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.taskList = new TaskList(); // TODO: Load from filePath
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    private void run() {
        ui.showInitialMessages();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                if ("".equals(input)) {
                    break;
                }
                Command cmd = parser.parse(input);
                isExit = cmd.isExit();
                cmd.execute(this);
            } catch (CattisException err) {
                ui.showError(err);
            } finally {
                ui.showLine();
            }
        }
        ui.showExitMessages();
    }
}
