package cattis;

import cattis.task.TaskList;
import cattis.ui.Ui;

/**
 * Represents an application instance for the main application <code>Cattis</code> and
 * its stub <code>CattisStub</code> for testing.
 */
public interface CattisInterface {
    TaskList getTaskList();

    Ui getUi();

    void run();
}