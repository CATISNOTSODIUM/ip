package cattis.exception;

/**
 * Root of all possible exceptions from this program
 */
public class CattisException extends Exception {
    // Error messages
    public static final String TASK_OUT_OF_BOUND = "Tasks out of bounded";
    public static final String EMPTY_FIELD = "Fields must not be left empty.";
    public static final String FORMAT_TODO = "todo [task]";
    public static final String FORMAT_DEADLINE = "deadline [task] /by [Deadline]";
    public static final String FORMAT_EVENT = "event [task] /from [Start] /to [End]";

    public static final String INCORRECT_FORMAT_TODO = "Invalid format.\nCorrect format: " + FORMAT_TODO;
    public static final String INCORRECT_FORMAT_DEADLINE = "Invalid format.\nCorrect format: " + FORMAT_DEADLINE;
    public static final String INCORRECT_FORMAT_EVENT = "Invalid format.\nCorrect format: " + FORMAT_EVENT;
    public CattisException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return String.format("⚠ OOPS! %s", super.getMessage());
    }
}
