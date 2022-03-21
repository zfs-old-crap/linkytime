package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.getTypicalLinkyTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.LinkyTime;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyLinkyTime_success() {
        final Model model = new ModelManager();
        final Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyLinkyTime_success() {
        final Model model = new ModelManager(new LinkyTime(), new UserPrefs());
        final Model expectedModel = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
        expectedModel.setLinkyTime(new LinkyTime());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
