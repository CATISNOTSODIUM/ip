package cattis.command;

import cattis.Cattis;
import cattis.CattisInterface;
import cattis.Constants;
import cattis.exception.CattisException;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        cattis.getTaskList().unmark(taskIndex);
        System.out.printf((Constants.UNMARK_TASK_MSG), cattis.getTaskList().get(taskIndex));
    }
}