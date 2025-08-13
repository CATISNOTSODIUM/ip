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
    private static void run() {
        init();
        // Main program
        while (true) {
            System.out.print(Constants.INPUT_DECORATOR);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (Constants.CMD_EXIT.equals(input)) {
                break;
            } else if (Constants.CMD_LIST.equals(input)){
                System.out.println(Cattis.taskList);
            } else {
                Cattis.taskList.add(new Task(input));
                System.out.printf((Constants.ADD_DECORATOR) + "\n", input);
            }
        }
    }
}
