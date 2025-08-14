public final class Constants {
    public static final String CHATBOT_NAME = "CATTIS \uD83D\uDC31";
    public static final String HORIZONTAL_LINE = "__________";

    public static final String GREETING_MSG = String.format(
            "Hello! I'm %s.\nWhat can I do for you?", CHATBOT_NAME);
    public static final String EXIT_MSG = "Bye. Hope to see you again soon!";
    public static final String LIST_TASK_MSG = "Here are the tasks in your list:";
    public static final String MARK_TASK_MSG = "Nice! I've marked this task as done:\n%s\n";
    public static final String UNMARK_TASK_MSG = "OK, I've marked this task as not done yet:\n%s\n";
    public static final String ADD_TASK_MSG = "Got it. I've added this task:\n%s\n";
    public static final String TASK_LIST_SUMMARY = "Now you have %s tasks in the list.\n";
    public static final String REMOVE_TASK_MSG = "Noted. I've removed this task:\n%s\n";
    public static final String ADD_DECORATOR = "added: %s";
    // Commands
    public static final String CMD_LIST = "list";
    public static final String CMD_EXIT = "bye";
    public static final String CMD_MARK = "mark";
    public static final String CMD_UNMARK = "unmark";
    public static final String CMD_DELETE = "delete";
    // Task types
    public static final String CMD_TODO = "todo";
    public static final String CMD_DEADLINE = "deadline";
    public static final String CMD_EVENT = "event";
}
