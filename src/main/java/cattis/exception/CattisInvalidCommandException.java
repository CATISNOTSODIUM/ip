package cattis.exception;

/**
 * Represents default (invalid) command
 */
public class CattisInvalidCommandException extends CattisException {
    /**
     * Default message for showing all possible commands
     */
    public CattisInvalidCommandException() {
        super("List of Commands\n"
                + "calendar: open calendar view\n"
                + "view [date]: view all tasks in the specific date\n"
                + "list: list all tasks\n"
                + "todo [task]: add a todo task\n"
                + "deadline [task] /by [Deadline]: add a task with a deadline\n"
                + "event [task] /from [Start] /to [End]: add an event with a time range\n"
                + "delete [index]: remove a task\n"
                + "mark [index]: mark a task as done\n"
                + "unmark [index]: mark a task as not done\n"
                + "find [keyword]: search tasks\n"
                + "bye: exit the application\n");
    }
}
