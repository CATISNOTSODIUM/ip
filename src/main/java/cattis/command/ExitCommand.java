package cattis.command;

import cattis.Cattis;
import cattis.CattisInterface;
import cattis.exception.CattisException;

public class ExitCommand extends Command {
    @Override
    public void execute(CattisInterface cattis) throws CattisException {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}