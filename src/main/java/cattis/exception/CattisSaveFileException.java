package cattis.Exceptions;

public class CattisSaveFileException extends CattisException {
    public CattisSaveFileException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return String.format("⚠ Failed to save file. %s", super.getMessage());
    }
}