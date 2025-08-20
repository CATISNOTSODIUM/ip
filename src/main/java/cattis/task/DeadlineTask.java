package cattis.task;

import cattis.exception.CattisException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    public static final String icon = "[D]";
    private LocalDate deadline;

    DeadlineTask(String taskName, String deadline) throws CattisException {
        super(taskName);
        setTime(deadline);
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
                + encodeDeadline();
    }

    @Override
    public String toString() {
        return icon + super.toString() + String.format(
                " (by: %s)", getDeadline());
    }

    public void setTime(String deadline) throws CattisException {
        try {
            var formatter = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMATTER);
            this.deadline = LocalDate.parse(deadline, formatter);
        } catch (DateTimeParseException err) {
            throw new CattisException("Failed to parse time for format " + DATE_TIME_INPUT_FORMATTER);
        }
    }

    public String getDeadline() {
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_OUTPUT_FORMATTER);
        return this.deadline == null
                ? "[No deadline]"
                : this.deadline.format(formatter);
    }

    public String encodeDeadline() {
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMATTER);
        return this.deadline == null
                ? "[No deadline]"
                : this.deadline.format(formatter);
    }
}