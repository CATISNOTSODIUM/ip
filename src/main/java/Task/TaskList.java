package Task;

import Exceptions.CattisException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    // index is one-indexed
    public Task get(int index) {
        return this.tasks.get(index - 1);
    }

    // index is one-indexed
    public void mark(int index) throws CattisException {
        if (index > this.count()) {
            throw new CattisException(CattisException.TASK_OUT_OF_BOUND);
        }
        this.tasks.get(index - 1).mark();
    }

    // index is one-indexed
    public void unmark(int index) throws CattisException {
        if (index > this.count()) {
            throw new CattisException(CattisException.TASK_OUT_OF_BOUND);
        }
        this.tasks.get(index - 1).unmark();
    }

    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "<empty>";
        }
        return IntStream.range(0, this.tasks.size())
                .mapToObj(index -> String.format("%s. %s", index + 1, this.tasks.get(index)))
                .collect(Collectors.joining("\n"));
    }

    public int count() {
        return this.tasks.size();
    }
}
