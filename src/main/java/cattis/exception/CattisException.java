package cattis.exception;

public class CattisException extends Exception {
    // Error messages
    public static final String TASK_OUT_OF_BOUND = "Tasks out of bounded";
    public static final String EMPTY_FIELD = "Fields must not be left empty.";
    public static final String INCORRECT_FORMAT_DEADLINE = "Invalid format.\nCorrect format: deadline [task] /by [Deadline]";
    public static final String INCORRECT_FORMAT_EVENT = "Invalid format.\nCorrect format: event [task] /from [Start] /to [End]";

    public CattisException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return String.format("⚠ OOPS! %s", super.getMessage());
    }
}
