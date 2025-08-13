package Task;

public class DeadlineTask extends Task {
    private static final String icon = "[D]";
    private final String deadline;
    DeadlineTask(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public static DeadlineTask createFromPrompt(String prompt) {
        String[] parts = prompt.split("/by", 2);
        if (parts.length != 2) {
            return null;
        } else {
            return new DeadlineTask(parts[0].trim(), parts[1].trim());
        }
    }

    @Override
    public String toString() {
        return icon + super.toString() + String.format(
                " (by: %s)", this.deadline
        );
    }
}