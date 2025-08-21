package cattis.command;

import cattis.CattisInterface;
import cattis.exception.CattisException;

/**
 * Represents the command to exit the program
 */
public class ExitCommand extends Command {
    @Override
    public void execute(CattisInterface cattis) throws CattisException {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
