package cattis;

import cattis.command.AddDeadlineTaskCommand;
import cattis.command.AddEventTaskCommand;
import cattis.command.AddTodoTaskCommand;
import cattis.command.Command;
import cattis.command.DeleteTaskCommand;
import cattis.command.ExitCommand;
import cattis.command.FindTaskCommand;
import cattis.command.ListCommand;
import cattis.command.MarkCommand;
import cattis.command.UnmarkCommand;
import cattis.exception.CattisException;
import cattis.exception.CattisParseException;

import java.util.Scanner;

/**
 * An instance to parse the input string into command
 */
public class Parser {
    public Parser() {
    }

    /**
     * Convert string to command for execution
     *
     * @param payload the string content to parse
     * @return Command the result command
     * @throws CattisException for parsing error
     */
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
        case "find":
            if (!scanner.hasNextLine()) {
                throw new CattisParseException(command);
            }
            remainingInput = scanner.nextLine();
            return new FindTaskCommand(remainingInput);
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

