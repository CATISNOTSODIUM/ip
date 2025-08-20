package cattis.ui;

import cattis.Constants;
import cattis.exception.CattisException;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showInitialMessages() {
        System.out.println(Constants.GREETING_MSG);
    }

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
