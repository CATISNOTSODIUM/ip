import Exceptions.CattisException;
import Task.*;

public class Cattis {
    private final Ui ui;
    private final Parser parser;
    private final Storage storage;
    private final TaskList taskList;
    private static final String DEFAULT_FILE_PATH = "data/cattis.txt";
    public static void main(String[] args) {
        Cattis cattis = new Cattis(DEFAULT_FILE_PATH);
        cattis.run();
    }

    public Cattis(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
        try {
            this.storage.load(this);
        } catch (CattisException err) {
            ui.showError(err);
        }
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Ui getUi() {
        return this.ui;
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
                if (cmd == null) {
                    ui.showMessage("Cannot parse input.");
                    continue;
                }
                isExit = cmd.isExit();
                cmd.execute(this);
                storage.save(this.taskList);
            } catch (CattisException err) {
                ui.showError(err);
            } finally {
                ui.showLine();
            }
        }
        ui.showExitMessages();
    }
}
