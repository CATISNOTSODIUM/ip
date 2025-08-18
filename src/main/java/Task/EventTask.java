package Task;

import Exceptions.CattisException;

public class EventTask extends Task {
    private static final String icon = "[E]";

    private final String startTime;
    private final String endTime;

    EventTask(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
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
        return icon + Task.SPLITER
                + super.getStatusIcon()
                + Task.SPLITER + super.getTaskName()
                + this.startTime + Task.SPLITER
                + this.endTime;
    }

    @Override
    public String toString() {
        return icon + super.toString() + String.format(
                " (from: %s to: %s)", this.startTime, this.endTime
        );
    }
}

