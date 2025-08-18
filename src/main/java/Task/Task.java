package Task;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Task {
    public static final String SPLITER = " | ";
    private final String taskName;
    private boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void mark() {
        this.isCompleted = true;
    }

    public void unmark() {
        this.isCompleted = false;
    }

    public String getStatusIcon() {
        return this.isCompleted ? "[X]" : "[ ]";
    }

    public String getTaskName() {
        return this.taskName;
    }

    // Encoded to task
    public abstract String toEncodedString();

    public Task decode(String payload) {
        List<String> arr = Arrays.stream(payload.split(Task.SPLITER))
                .map(String::trim).collect(Collectors.toList());
        // TODO: Check parsing error
        // return new TodoTask(arr);
        return null;
    }
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.taskName;
    }
}

