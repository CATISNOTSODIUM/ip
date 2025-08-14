import Exceptions.CattisException;
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

    public static TaskList getTaskList() {
        return Cattis.taskList;
    }

    private static void run() {
        init();
        // Main program
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.next();
                Command cmd = Command.fromString(input);
                if (cmd.isExit()) {
                    break;
                } else if (cmd.isDefault()) {
                    // read the rest of the line and echo
                    String remainingInput = scanner.nextLine();
                    input += remainingInput;
                    throw new CattisException("Invalid command " + input);
                } else {
                    cmd.execute(scanner);
                }
                if (!scanner.hasNextLine()) {
                    break;
                }
            } catch (CattisException err) {
                System.out.println(err.toString());
            }
        }
    }
}
