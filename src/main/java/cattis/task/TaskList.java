package cattis.task;

import cattis.exception.CattisException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {
    private List<Task> tasks;

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

    // index is one-indexed
    public Task delete(int index) throws CattisException {
        if (index > this.count()) {
            throw new CattisException(CattisException.TASK_OUT_OF_BOUND);
        }
        Task deletedTask = this.tasks.get(index - 1);
        this.tasks.remove(index - 1);
        return deletedTask;
    }

    public void taskListSummary() {
        String TASK_LIST_SUMMARY = "Now you have %s tasks in the list.\n";
        System.out.printf(TASK_LIST_SUMMARY, this.count());
    }

    public String toEncodedString() {
        return tasks.stream().
                map(Task::toEncodedString).
                collect(Collectors.joining("\n"));
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

    public String listByName(String name) {
        StringBuilder result = new StringBuilder();
        int index = 1;

        for (Task task : this.tasks) {
            if (task.getTaskName().toLowerCase().contains(name.toLowerCase())) {
                result.append(index).append(". ").append(task);
            }
            index++;
        }
        return result.toString();
    }
}
