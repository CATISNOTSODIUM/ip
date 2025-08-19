package cattis.Exceptions;

public class CattisLoadFileException extends CattisException {
    public CattisLoadFileException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return String.format("⚠ Failed to load file. %s", super.getMessage());
    }
}