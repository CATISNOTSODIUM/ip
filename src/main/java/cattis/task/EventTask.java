package cattis.Task;

import cattis.Exceptions.CattisException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    public static final String icon = "[E]";

    private LocalDate startTime;
    private LocalDate endTime;

    EventTask(String taskName, String startTime, String endTime) throws CattisException {
        super(taskName);
        setTime(startTime, endTime);
    }

    public static EventTask createFromPrompt(String prompt) throws CattisException {
        String taskDescription = null;
        String startTime = null;
        String endTime = null;
        String[] parts = prompt.split("/from", 2);
        if (parts.length != 2) {
            throw new CattisException(CattisException.INCORRECT_FORMAT_EVENT);
        }
        taskDescription = parts[0].trim();
        parts = parts[1].trim().split("/to", 2);
        if (parts.length != 2) {
            throw new CattisException(CattisException.INCORRECT_FORMAT_EVENT);
        }
        startTime = parts[0].trim();
        endTime = parts[1].trim();
        if (taskDescription.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new CattisException(CattisException.EMPTY_FIELD);
        }
        return new EventTask(taskDescription, startTime, endTime);
    }

    @Override
    public String toEncodedString() {
        return icon + Task.SPLITTER
                + super.getStatusIcon()
                + Task.SPLITTER + super.getTaskName() + Task.SPLITTER
                + encodeStartTime() + Task.SPLITTER
                + encodeEndTime();
    }

    @Override
    public String toString() {
        return icon + super.toString() + String.format(
                " (from: %s to: %s)", getStartTime(), getEndTime()
        );
    }


    public void setTime(String startTime, String endTime) throws CattisException {
        try {
            var formatter = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMATTER);
            this.startTime = LocalDate.parse(startTime, formatter);
            this.endTime = LocalDate.parse(endTime, formatter);
        } catch (DateTimeParseException err) {
            throw new CattisException("Failed to parse time for " + DATE_TIME_INPUT_FORMATTER);
        }
    }

    public String getStartTime() {
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_OUTPUT_FORMATTER);
        return this.startTime == null
                ? "[No start time]"
                : this.startTime.format(formatter);
    }

    public String encodeStartTime() {
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMATTER);
        return this.startTime == null
                ? "[No start time]"
                : this.startTime.format(formatter);
    }

    public String encodeEndTime() {
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMATTER);
        return this.startTime == null
                ? "[No end time]"
                : this.startTime.format(formatter);
    }

    public String getEndTime() {
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_OUTPUT_FORMATTER);
        return this.endTime == null
                ? "[No end time]"
                : this.endTime.format(formatter);
    }
}

