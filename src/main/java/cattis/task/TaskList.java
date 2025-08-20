package cattis.task;

import cattis.exception.CattisException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A class that encapsulate the actual list of tasks
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add new task to the list
     *
     * @param task the <code>Task</code> instance to be added in the list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieve the task from the specific index
     * @param index one-indexed position to retrieve from the list
     * @return <code>Task</code> instance
     */
    public Task get(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Mark the task from the specific index as finished.
     * @param index one-indexed position to mark as finished
     */
    public void mark(int index) throws CattisException {
        if (index > this.count()) {
            throw new CattisException(CattisException.TASK_OUT_OF_BOUND);
        }
        this.tasks.get(index - 1).mark();
    }

    /**
     * Mark the task from the specific index as unfinished.
     * @param index one-indexed position to mark as unfinished
     */
    public void unmark(int index) throws CattisException {
        if (index > this.count()) {
            throw new CattisException(CattisException.TASK_OUT_OF_BOUND);
        }
        this.tasks.get(index - 1).unmark();
    }

    /**
     * Delete the task from the specific index
     * @param index one-indexed position to delete from the list
     * @return <code>Task</code> instance that has been deleted
     */
    public Task delete(int index) throws CattisException {
        if (index > this.count()) {
            throw new CattisException(CattisException.TASK_OUT_OF_BOUND);
        }
        Task deletedTask = this.tasks.get(index - 1);
        this.tasks.remove(index - 1);
        return deletedTask;
    }

    /**
     * Quickly prints out the number of elements in the list
     */
    public void taskListSummary() {
        String TASK_LIST_SUMMARY = "Now you have %s tasks in the list.\n";
        System.out.printf(TASK_LIST_SUMMARY, this.count());
    }

    /**
     * Encode task list as string to be saved as text file
     * @return the encoded string
     */
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


    /**
     * Search all tasks whose task name contains the keyword <code>name</code>.
     * @param name keyword to search
     * @return list of filtered tasks concatenated as string
     */
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
