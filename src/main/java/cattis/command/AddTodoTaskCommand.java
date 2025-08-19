package cattis.command;

import cattis.Cattis;
import cattis.Constants;
import cattis.exception.CattisException;
import cattis.task.Task;
import cattis.task.TodoTask;

public class AddTodoTaskCommand extends AddTaskCommand {
    private final String taskName;

    public AddTodoTaskCommand(String taskName) {
        this.taskName = taskName.trim();
    }

    @Override
    public void execute(Cattis cattis) throws CattisException {
        Task newTask = TodoTask.createFromPrompt(taskName);
        cattis.getTaskList().add(newTask);
        System.out.printf((Constants.ADD_TASK_MSG), newTask);
        cattis.getTaskList().taskListSummary();
    }
}