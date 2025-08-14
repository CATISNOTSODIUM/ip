import Exceptions.CattisException;
import Task.*;
import java.util.Scanner;

public class Handler {
    private static void taskListSummary() {
        System.out.printf(Constants.TASK_LIST_SUMMARY,
                Cattis.getTaskList().count());
    }

    public static void handleList() {
        System.out.println(Constants.LIST_TASK_MSG);
        System.out.println(Cattis.getTaskList());
    }

    public static void handleMark(Scanner scanner)
            throws CattisException {
        int taskIndex = scanner.nextInt();
        Cattis.getTaskList().mark(taskIndex);
        System.out.printf((Constants.MARK_TASK_MSG), Cattis.getTaskList().get(taskIndex));
    }

    public static void handleUnmark(Scanner scanner)
            throws CattisException {
        int taskIndex = scanner.nextInt();
        Cattis.getTaskList().unmark(taskIndex);
        System.out.printf((Constants.UNMARK_TASK_MSG),
                Cattis.getTaskList().get(taskIndex));
    }

    public static void handleTodoTask(Scanner scanner)
            throws CattisException {
        String taskName = scanner.nextLine();
        Task newTask = TodoTask.createFromPrompt(taskName);
        Cattis.getTaskList().add(newTask);
        System.out.printf((Constants.ADD_TASK_MSG), newTask);
        taskListSummary();
    }

    public static void handleDeadlineTask(Scanner scanner)
            throws CattisException {
        String remainingInput = scanner.nextLine();
        Task newTask = DeadlineTask.createFromPrompt(remainingInput);
        Cattis.getTaskList().add(newTask);
        System.out.printf((Constants.ADD_TASK_MSG), newTask);
        taskListSummary();
    }

    public static void handleEventTask(Scanner scanner) throws CattisException {
        String remainingInput = scanner.nextLine();
        Task newTask = EventTask.createFromPrompt(remainingInput);
        Cattis.getTaskList().add(newTask);
        System.out.printf((Constants.ADD_TASK_MSG), newTask);
        taskListSummary();
    }

    public static void handleDelete(Scanner scanner) throws CattisException {
        int taskIndex = scanner.nextInt();
        Task deletedTask = Cattis.getTaskList().delete(taskIndex);
        System.out.printf((Constants.REMOVE_TASK_MSG), deletedTask);
        taskListSummary();
    }
}
