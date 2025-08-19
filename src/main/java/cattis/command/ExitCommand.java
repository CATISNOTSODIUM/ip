package cattis.command;

import cattis.Cattis;
import cattis.exception.CattisException;

public class ExitCommand extends Command {
    @Override
    public void execute(Cattis cattis) throws CattisException {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}