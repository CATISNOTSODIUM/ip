import Task.*;
import java.util.*;

public class Cattis {

    private static TaskList taskList;
    public static void main(String[] args) {
        System.out.println(Constants.GREETING_MSG);
        run();
        System.out.println(Constants.EXIT_MSG);
    }

    private static void init() {
        Cattis.taskList = new TaskList();
    }

    private static void taskListSummary() {
        System.out.printf(Constants.TASK_LIST_SUMMARY, Cattis.taskList.count());
    }

    private static void run() {
        init();
        // Main program
        while (true) {
            System.out.print(Constants.INPUT_DECORATOR);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            // Dispatching based on input
            if (Constants.CMD_EXIT.equals(input)) {
                break;
            } else if (Constants.CMD_LIST.equals(input)) {
                System.out.println(Constants.LIST_TASK_MSG);
                System.out.println(Cattis.taskList);
            } else if (Constants.CMD_MARK.equals(input)) {
                int taskIndex = scanner.nextInt();
                Cattis.taskList.mark(taskIndex);
                System.out.printf((Constants.MARK_TASK_MSG), Cattis.taskList.get(taskIndex));
            } else if (Constants.CMD_UNMARK.equals(input)) {
                int taskIndex = scanner.nextInt();
                Cattis.taskList.unmark(taskIndex);
                System.out.printf((Constants.UNMARK_TASK_MSG), Cattis.taskList.get(taskIndex));
            } else if (Constants.CMD_TODO.equals(input)) {
                String taskName = scanner.nextLine();
                Task newTask = new TodoTask(taskName);
                Cattis.taskList.add(newTask);
                System.out.printf((Constants.ADD_TASK_MSG), newTask);
                taskListSummary();
            } else if (Constants.CMD_DEADLINE.equals(input)) {
                String remainingInput = scanner.nextLine();
                Task newTask = DeadlineTask.createFromPrompt(remainingInput);
                Cattis.taskList.add(newTask);
                System.out.printf((Constants.ADD_TASK_MSG), newTask);
                taskListSummary();
            } else if (Constants.CMD_EVENT.equals(input)) {
                String remainingInput = scanner.nextLine();
                Task newTask = EventTask.createFromPrompt(remainingInput);
                Cattis.taskList.add(newTask);
                System.out.printf((Constants.ADD_TASK_MSG), newTask);
                taskListSummary();
            } else {
                // read the rest of the line and echo
                String remainingInput = scanner.nextLine();
                input += remainingInput;
                System.out.println("Invalid command " + input);
            }
        }
    }
}
