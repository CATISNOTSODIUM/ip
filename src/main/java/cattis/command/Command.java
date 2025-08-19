package cattis.command;

import cattis.Cattis;
import cattis.exception.CattisException;

public abstract class Command {
    public abstract void execute(Cattis cattis) throws CattisException;
    public boolean isExit() {
        return false;
    }
}
