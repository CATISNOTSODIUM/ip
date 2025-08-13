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

    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "<empty>";
        }
        return IntStream.range(0, this.tasks.size())
                .mapToObj(index -> String.format("%s. %s", index + 1, this.tasks.get(index)))
                .collect(Collectors.joining("\n"));
    }
}
