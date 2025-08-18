import Exceptions.CattisException;
import Task.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class Command {
    public abstract void execute(Cattis cattis) throws CattisException;

    public boolean isExit() {
        return false;
    }
}

class ListCommand extends Command {
    @Override
    public void execute(Cattis cattis) throws CattisException {
        System.out.println(Constants.LIST_TASK_MSG);
        System.out.println(cattis.getTaskList());
    }
}

class ExitCommand extends Command  {
    @Override
    public void execute(Cattis cattis) throws CattisException {}

    @Override
    public boolean isExit() {
        return true;
    }
}

class MarkCommand extends Command {
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

class UnmarkCommand extends Command {
    private final int taskIndex;
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(Cattis cattis) throws CattisException {
        cattis.getTaskList().unmark(taskIndex);
        System.out.printf((Constants.UNMARK_TASK_MSG), cattis.getTaskList().get(taskIndex));
    }
}

class DeleteTaskCommand extends Command {
    private final int taskIndex;
    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(Cattis cattis) throws CattisException {
        Task deletedTask = cattis.getTaskList().delete(taskIndex);
        System.out.printf((Constants.REMOVE_TASK_MSG), deletedTask);
        cattis.getTaskList().taskListSummary();
    }
}

abstract class AddTaskCommand extends Command {}

class AddTodoTaskCommand extends AddTaskCommand {
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

class AddDeadlineTaskCommand extends AddTaskCommand {
    private final String taskName;
    public AddDeadlineTaskCommand(String taskName) {
        this.taskName = taskName.trim();
    }
    @Override
    public void execute(Cattis cattis) throws CattisException {
        Task newTask = DeadlineTask.createFromPrompt(taskName);
        cattis.getTaskList().add(newTask);
        System.out.printf((Constants.ADD_TASK_MSG), newTask);
        cattis.getTaskList().taskListSummary();
    }
}

class AddEventTaskCommand extends AddTaskCommand {
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
