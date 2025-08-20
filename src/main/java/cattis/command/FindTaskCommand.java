package cattis.command;

import cattis.CattisInterface;
import cattis.Constants;
import cattis.exception.CattisException;
import cattis.task.Task;
import cattis.task.TaskList;
import cattis.task.TodoTask;

public class FindTaskCommand extends Command{
    private final String taskName;

    public FindTaskCommand(String taskName) {
        this.taskName = taskName.trim();
    }

    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        cattis.getUi().showMessage(cattis.getTaskList().listByName(this.taskName));
    }
}