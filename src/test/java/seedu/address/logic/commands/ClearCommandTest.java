package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetingEntries.getTypicalLinkyTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.LinkyTime;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyLinkyTime_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyLinkyTime_success() {
        Model model = new ModelManager(new UserPrefs(), new LinkyTime());
        Model expectedModel = new ModelManager(new UserPrefs(), getTypicalLinkyTime());
        expectedModel.setLinkyTime(new LinkyTime());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
