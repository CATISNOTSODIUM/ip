package Task;

public class TodoTask extends Task {
    private static final String icon = "[T]";

    public TodoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return icon + super.toString();
    }
}
