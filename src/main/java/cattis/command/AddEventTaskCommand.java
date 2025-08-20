package cattis.command;

import cattis.CattisInterface;
import cattis.Constants;
import cattis.exception.CattisException;
import cattis.task.EventTask;
import cattis.task.Task;

/**
 * Represents the command to add <code>EventTasj</code> to the
 * task list <code>Cattis.getTaskList()</code> instance.
 */
public class AddEventTaskCommand extends AddTaskCommand {
    private final String taskName;

    public AddEventTaskCommand(String taskName) {
        this.taskName = taskName.trim();
    }

    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        Task newTask = EventTask.createFromPrompt(taskName);
        cattis.getTaskList().add(newTask);
        System.out.printf((Constants.ADD_TASK_MSG), newTask);
        cattis.getTaskList().taskListSummary();
    }
}
