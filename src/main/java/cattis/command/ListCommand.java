package cattis.command;

import cattis.Cattis;
import cattis.CattisInterface;
import cattis.Constants;
import cattis.exception.CattisException;

public class ListCommand extends Command {
    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        System.out.println(Constants.LIST_TASK_MSG);
        System.out.println(cattis.getTaskList());
    }
}