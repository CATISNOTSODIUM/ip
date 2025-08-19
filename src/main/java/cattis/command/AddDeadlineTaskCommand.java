package cattis.command;

import cattis.Cattis;
import cattis.CattisInterface;
import cattis.Constants;
import cattis.exception.CattisException;
import cattis.task.DeadlineTask;
import cattis.task.Task;

public class AddDeadlineTaskCommand extends AddTaskCommand {
    private final String taskName;

    public AddDeadlineTaskCommand(String taskName) {
        this.taskName = taskName.trim();
    }

    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        Task newTask = DeadlineTask.createFromPrompt(taskName);
        cattis.getTaskList().add(newTask);
        System.out.printf((Constants.ADD_TASK_MSG), newTask);
        cattis.getTaskList().taskListSummary();
    }
}
