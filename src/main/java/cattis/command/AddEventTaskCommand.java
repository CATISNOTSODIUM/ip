package cattis.command;

import cattis.Cattis;
import cattis.Constants;
import cattis.exception.CattisException;
import cattis.task.EventTask;
import cattis.task.Task;

public class AddEventTaskCommand extends AddTaskCommand {
    private final String taskName;

    public AddEventTaskCommand(String taskName) {
        this.taskName = taskName.trim();
    }

    @Override
    public void execute(Cattis cattis) throws CattisException {
        Task newTask = EventTask.createFromPrompt(taskName);
        cattis.getTaskList().add(newTask);
        System.out.printf((Constants.ADD_TASK_MSG), newTask);
        cattis.getTaskList().taskListSummary();
    }
}
