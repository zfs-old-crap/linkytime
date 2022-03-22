package seedu.address.logic.commands.meeting;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.meeting.TypicalMeetings.getTypicalLinkyTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.LinkyTime;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearMeetingCommandTest {

    @Test
    public void execute_emptyLinkyTime_success() {
        final Model model = new ModelManager();
        final Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearMeetingCommand(), model, ClearMeetingCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyLinkyTime_success() {
        final Model model = new ModelManager(new LinkyTime(), new UserPrefs());
        final Model expectedModel = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
        expectedModel.setLinkyTime(new LinkyTime());

        assertCommandSuccess(new ClearMeetingCommand(), model, ClearMeetingCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
