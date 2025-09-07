package cattis.ui;

import java.util.Scanner;

import cattis.Constants;
import cattis.exception.CattisException;

/**
 * Handle all user input / output logics
 */
public class Ui {
    private final Scanner scanner;
    private String latestMessages;

    /**
     * default constructor, reset {@code messages}
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.resetMessages();
        assert "".equals(this.latestMessages);
    }

    public void showInitialMessages() {
        showMessage(Constants.GREETING_MSG);
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
        this.showMessage(err.toString());
    }

    public String getLatestMessage() {
        return "".equals(this.latestMessages)
                ? "<empty>"
                : this.latestMessages;
    }

    public void resetMessages() {
        this.latestMessages = "";
    }

    /**
     * Add message to the {@code latestMessages} while printing the result.
     * @param msg message to show
     */
    public void showMessage(String msg) {
        this.latestMessages += msg;
    }

    /**
     * displaying exit message
     */
    public void showExitMessages() {
        this.showMessage(Constants.EXIT_MSG);
    }
}
