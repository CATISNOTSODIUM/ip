package Task;

import Exceptions.CattisException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TodoTask extends Task {
    private static final String icon = "[T]";

    public TodoTask(String taskName) {
        super(taskName);
    }

    public static TodoTask createFromPrompt(String prompt) throws CattisException {
        if (prompt.isEmpty()) {
            throw new CattisException(CattisException.EMPTY_FIELD);
        } else {
            return new TodoTask(prompt);
        }
    }

    @Override
    public String toEncodedString() {
        return icon + Task.SPLITER + super.getStatusIcon() + Task.SPLITER + super.getTaskName();
    }

    @Override
    public String toString() {
        return icon + super.toString();
    }
}
