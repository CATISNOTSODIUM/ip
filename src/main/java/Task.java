import java.util.List;

public class Task {
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

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.taskName;
    }
}

