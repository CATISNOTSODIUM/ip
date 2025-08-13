package Task;

public class EventTask extends Task {
    private static final String icon = "[E]";

    private final String startTime;
    private final String endTime;

    EventTask(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static EventTask createFromPrompt(String prompt) {
        String taskDescription = null;
        String startTime = null;
        String endTime = null;
        String[] parts = prompt.split("/from", 2);
        if (parts.length != 2) {
            return null;
        }
        taskDescription = parts[0].trim();
        parts = parts[1].trim().split("/to", 2);
        if (parts.length != 2) {
            return null;
        }
        startTime = parts[0].trim();
        endTime = parts[1].trim();
        return new EventTask(taskDescription, startTime, endTime);
    }

    @Override
    public String toString() {
        return icon + super.toString() + String.format(
                " (from: %s to: %s)", this.startTime, this.endTime
        );
    }
}

