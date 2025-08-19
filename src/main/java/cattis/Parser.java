package cattis;

import cattis.command.*;
import cattis.exception.CattisException;
import cattis.exception.CattisParseException;

import java.util.Scanner;

public class Parser {
    public Parser() {}

    // parse the entire line
    public Command parse(String payload) throws CattisException {
        Scanner scanner = new Scanner(payload);
        String command = scanner.next();
        int taskIndex;
        String remainingInput;
        switch (command) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "mark":
                if (!scanner.hasNextInt()) {
                    throw new CattisParseException(command);
                }
                taskIndex = scanner.nextInt();
                return new MarkCommand(taskIndex);
            case "unmark":
                if (!scanner.hasNextInt()) {
                    throw new CattisParseException(command);
                }
                taskIndex = scanner.nextInt();
                return new UnmarkCommand(taskIndex);
            case "delete":
                if (!scanner.hasNextInt()) {
                    throw new CattisParseException(command);
                }
                taskIndex = scanner.nextInt();
                return new DeleteTaskCommand(taskIndex);
            case "todo":
                if (!scanner.hasNextLine()) {
                    throw new CattisParseException(command);
                }
                remainingInput = scanner.nextLine();
                return new AddTodoTaskCommand(remainingInput);
            case "deadline":
                if (!scanner.hasNextLine()) {
                    throw new CattisParseException(command);
                }
                remainingInput = scanner.nextLine();
                return new AddDeadlineTaskCommand(remainingInput);
            case "event":
                if (!scanner.hasNextLine()) {
                    throw new CattisParseException(command);
                }
                remainingInput = scanner.nextLine();
                return new AddEventTaskCommand(remainingInput);
        }
        return null;
    }
}

