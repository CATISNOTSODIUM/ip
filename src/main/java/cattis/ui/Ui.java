package cattis.ui;

import java.util.Scanner;

import cattis.Constants;
import cattis.exception.CattisException;

/**
 * Handle all user input / output logics
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showInitialMessages() {
        System.out.println(Constants.GREETING_MSG);
    }

    /**
     * Reads the input from the console (by line)
     * @return the string input by user
     */
    public String readCommand() {
        if (!scanner.hasNextLine()) {
            return "";
        }
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("----");
    }

    public void showError(CattisException err) {
        System.out.println(err.toString());
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showExitMessages() {
        System.out.println(Constants.EXIT_MSG);
    }
}
