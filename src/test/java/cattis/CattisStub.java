package cattis;

import cattis.task.TaskList;
import cattis.ui.Ui;

public class CattisStub implements CattisInterface {
    private final Ui ui;
    private final TaskList taskList;

    public CattisStub() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Ui getUi() {
        return this.ui;
    }

    public void run() {

    }
}

