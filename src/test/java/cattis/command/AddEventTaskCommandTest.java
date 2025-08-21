package cattis.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import cattis.CattisInterface;
import cattis.CattisStub;
import cattis.exception.CattisException;
import cattis.task.Task;

public class AddEventTaskCommandTest {

    @Test
    public void addEventTask_missingEndDate_exceptionThrown() {
        try {
            AddTaskCommand cmd = new AddEventTaskCommand("task 1 /from 2020-10-10");
            CattisInterface cattis = new CattisStub();
            cmd.execute(cattis);
            fail();
        } catch (CattisException err) {
            assertEquals(CattisException.INCORRECT_FORMAT_EVENT, err.getMessage());
        }
    }

    @Test
    public void addEventTask_invalidDate_exceptionThrown() {
        try {
            AddTaskCommand cmd = new AddEventTaskCommand("task 1 /from 2020-10-10 /to invalid date");
            CattisInterface cattis = new CattisStub();
            cmd.execute(cattis);
            fail();
        } catch (CattisException err) {
            String errMsg = "Failed to parse time for " + Task.DATE_TIME_INPUT_FORMATTER;
            assertEquals(errMsg, err.getMessage());
        }
    }

    @Test
    public void addEventTask_endDateBeforeStartDate_exceptionThrown() {
        try {
            AddTaskCommand cmd = new AddEventTaskCommand("task 1 /from 2020-12-10 /to 2020-10-10");
            CattisInterface cattis = new CattisStub();
            cmd.execute(cattis);
            fail();
        } catch (CattisException err) {
            String errMsg = "Start time must be before end time";
            assertEquals(errMsg, err.getMessage());
        }
    }

    @Test
    public void addEventTask_success() {
        try {
            AddTaskCommand cmd = new AddEventTaskCommand("task 1 /from 2020-12-10 /to 2020-12-11");
            CattisInterface cattis = new CattisStub();
            cmd.execute(cattis);
        } catch (CattisException err) {
            fail();
        }
    }
}
