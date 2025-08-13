import constants.Constants;
import java.util.*;

public class Cattis {
    public static void main(String[] args) {
        System.out.println(Constants.GREETING_MSG);
        run();
        System.out.println(Constants.EXIT_MSG);
    }

    private static void run() {
        // Main program
        while (true) {
            System.out.print(Constants.INPUT_DECORATOR);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (Constants.EXIT_INPUT.equals(input)) {
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
