import Exceptions.CattisException;

import java.util.Scanner;

public enum Command {
    LIST(Constants.CMD_LIST) {
        @Override
        public void execute(Scanner scanner) throws CattisException {
            Handler.handleList();
        }
    },
    EXIT(Constants.CMD_EXIT),
    MARK(Constants.CMD_MARK) {
        @Override
        public void execute(Scanner scanner) throws CattisException {
            Handler.handleMark(scanner);
        }
    },
    UNMARK(Constants.CMD_UNMARK) {
        @Override
        public void execute(Scanner scanner) throws CattisException {
            Handler.handleUnmark(scanner);
        }
    },
    DELETE(Constants.CMD_DELETE) {
        @Override
        public void execute(Scanner scanner) throws CattisException {
            Handler.handleDelete(scanner);
        }
    },
    TODO(Constants.CMD_TODO) {
        @Override
        public void execute(Scanner scanner) throws CattisException {
            Handler.handleTodoTask(scanner);
        }
    },
    DEADLINE(Constants.CMD_DEADLINE) {
        @Override
        public void execute(Scanner scanner) throws CattisException {
            Handler.handleDeadlineTask(scanner);
        }
    },
    EVENT(Constants.CMD_EVENT) {
        @Override
        public void execute(Scanner scanner) throws CattisException {
            Handler.handleEventTask(scanner);
        }
    },
    DEFAULT(Constants.CMD_DEFAULT);

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command fromString(String input) {
        for (Command cmd : values()) {
            if (cmd.command.equals(input)) {
                return cmd;
            }
        }
        return DEFAULT;
    }

    public void execute(Scanner scanner) throws CattisException {}

    public boolean isExit() {
        return Constants.CMD_EXIT.equals(this.command);
    }

    public boolean isDefault() {
        return Constants.CMD_DEFAULT.equals(this.command);
    }
}
