package cattis.command;

import cattis.Cattis;
import cattis.Constants;
import cattis.exception.CattisException;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Cattis cattis) throws CattisException {
        cattis.getTaskList().mark(taskIndex);
        System.out.printf((Constants.MARK_TASK_MSG), cattis.getTaskList().get(taskIndex));
    }
}