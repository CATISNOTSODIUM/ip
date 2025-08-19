package cattis.Task;

import cattis.Exceptions.CattisException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public abstract class Task {
    public static final String SPLITTER = "<>";
    // Date time
    public static final String DATE_TIME_INPUT_FORMATTER = "yyyy-MM-dd";
    public static final String DATE_TIME_OUTPUT_FORMATTER = "MMM dd yyyy";
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

    public static Task decode(String payload) throws CattisException {
        // TYPE | STATUS | TASK_NAME | DEADLINE / START DATE | END DATE
        List<String> arr = Arrays.stream(payload.split(Task.SPLITTER))
                .map(String::trim).collect(Collectors.toList());
        if (arr.size() < 3) {
            throw new CattisException("Failed to load task from disk");
        }
        String taskType = arr.get(0);
        boolean status;
        status = "[X]".equals(arr.get(1));
        String taskName = arr.get(2);
        switch (taskType) {
            case TodoTask.icon:
                return new TodoTask(taskName, status);
            case DeadlineTask.icon:
                if (arr.size() != 4) {
                    throw new CattisException("Failed to load task from disk");
                }
                return new DeadlineTask(taskName, arr.get(3));
            case EventTask.icon:
                if (arr.size() != 5) {
                    throw new CattisException("Failed to load task from disk");
                }
                return new EventTask(taskName, arr.get(3), arr.get(4));
        }
        return null;
    }
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.taskName;
    }
}

