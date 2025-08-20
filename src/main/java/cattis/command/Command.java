package cattis.command;

import cattis.CattisInterface;
import cattis.exception.CattisException;

/**
 * An abstract class representing all possible commands in <code>Cattis</code>
 */
public abstract class Command {
    /**
     * Execute the command and modify <code>taskList</code> in the <code>cattis</code> instance
     *
     * @param cattis application instance
     * @throws CattisException for execution error
     */
    public abstract void execute(CattisInterface cattis) throws CattisException;

    public boolean isExit() {
        return false;
    }
}
