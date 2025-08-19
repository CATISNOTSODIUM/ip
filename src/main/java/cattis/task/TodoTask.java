package cattis.Task;

import cattis.Exceptions.CattisException;

public class TodoTask extends Task {
    public static final String icon = "[T]";

    public TodoTask(String taskName) {
        super(taskName);
    }

    public TodoTask(String taskName, boolean status) {
        super(taskName);
        if (status) {
            this.mark();
        } else {
            this.unmark();
        }
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
        return icon + Task.SPLITTER + super.getStatusIcon() + Task.SPLITTER + super.getTaskName();
    }

    @Override
    public String toString() {
        return icon + super.toString();
    }
}
