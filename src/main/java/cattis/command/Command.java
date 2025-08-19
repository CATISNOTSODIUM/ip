package cattis.command;

import cattis.Cattis;
import cattis.CattisInterface;
import cattis.exception.CattisException;

public abstract class Command {
    public abstract void execute(CattisInterface cattis) throws CattisException;
    public boolean isExit() {
        return false;
    }
}
