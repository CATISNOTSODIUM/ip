package cattis.exception;

/**
 * Represents time-related parsing errors
 */
public class CattisInvalidTimeException extends CattisException {
    public CattisInvalidTimeException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return String.format(PARSE_TIME, super.getMessage());
    }
}
