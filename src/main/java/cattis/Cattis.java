package cattis;

import cattis.command.Command;
import cattis.exception.CattisException;
import cattis.task.TaskList;
import cattis.ui.Ui;

/**
 * The main instance of the application
 */
public class Cattis implements CattisInterface {
    private static final String DEFAULT_FILE_PATH = "data/cattis.txt";
    private final Ui ui;
    private final Parser parser;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Default constructor based on the default file path
     */
    public Cattis() {
        this(Cattis.DEFAULT_FILE_PATH);
    }

    /**
     * Constructs a new {@code Cattis} object.
     * <p>
     * This constructor initializes the core components of the application:
     * {@link Ui}, {@link Parser}, {@link Storage}, and {@link TaskList}.
     * It attempts to load any existing tasks from the specified file path. If
     * an error occurs during the loading process (e.g., the file is not found
     * or the data is corrupted), a {@link CattisException} is caught, and an
     * error message is displayed to the user via the {@link Ui}.
     *
     * @param filePath The file path to the data file where tasks are stored.
     */
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

    public static void main(String[] args) {
        Cattis cattis = new Cattis(DEFAULT_FILE_PATH);
        assert cattis.getUi() != null;
        cattis.run();
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Ui getUi() {
        return this.ui;
    }

    /**
     * Running a single command (for non CLI applications)
     * @param input input from user
     * @return boolean true if {@code isExit} is true
     */
    public boolean runOneCommand(String input) {
        try {
            boolean isExit = false;
            Command cmd = parser.parse(input);
            if (cmd == null) {
                ui.showMessage("Cannot parse input: " + input);
                return false;
            }
            isExit = cmd.isExit();
            if (isExit) {
                return true;
            }
            cmd.execute(this);
            storage.save(this.taskList);
        } catch (CattisException err) {
            ui.showError(err);
        }
        return false;
    }

    /**
     * Initiate the application life cycle with the following stages
     * <p>
     * 1. Initialization: show initial message <p>
     * 2. Receive and parse user input to retrieve {@link Command} <p>
     * 3. Execute the command
     */
    public void run() {
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
                    ui.showMessage("Cannot parse input: " + input);
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
