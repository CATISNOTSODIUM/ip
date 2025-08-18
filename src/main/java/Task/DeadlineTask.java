package Task;

import Exceptions.CattisException;

public class DeadlineTask extends Task {
    public static final String icon = "[D]";
    private final String deadline;
    DeadlineTask(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public static DeadlineTask createFromPrompt(String prompt) throws CattisException {
        String[] parts = prompt.split("/by", 2);
        if (parts.length != 2) {
            throw new CattisException(CattisException.INCORRECT_FORMAT_DEADLINE);
        } else {
            if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new CattisException(CattisException.EMPTY_FIELD);
            }
            return new DeadlineTask(parts[0].trim(), parts[1].trim());
        }
    }

    @Override
    public String toEncodedString() {
        return icon + Task.SPLITTER
                + super.getStatusIcon()
                + Task.SPLITTER + super.getTaskName() + Task.SPLITTER
                + this.deadline;
    }

    @Override
    public String toString() {
        return icon + super.toString() + String.format(
                " (by: %s)", this.deadline
        );
    }
}