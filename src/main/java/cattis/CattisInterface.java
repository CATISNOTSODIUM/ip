package cattis;

import cattis.task.TaskList;
import cattis.ui.Ui;

public interface CattisInterface {
    TaskList getTaskList();

    Ui getUi();

    void run();
}