package seedu.address.logic.commands.meeting;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.typical.TypicalLinkyTime.getTypicalLinkyTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListMeetingCommand.
 */
public class ListMeetingCommandTest {

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        assertCommandSuccess(new ListMeetingCommand(), model, new CommandResult(ListMeetingCommand.MESSAGE_SUCCESS,
                CommandResult.MeetingListStatusResult.UPCOMING), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);
        assertCommandSuccess(new ListMeetingCommand(), model, new CommandResult(ListMeetingCommand.MESSAGE_SUCCESS,
                CommandResult.MeetingListStatusResult.UPCOMING), expectedModel);
    }

    // This section is to be uncommented and filled up once all features have been integrated and merged.
    // @Test
    // public void execute_listHasOneCompletedMeeting_showsNothing() {
    //
    // }
    //
    // @Test
    // public void execute_listHasOneCompletedAndUncompletedMeeting_showsOneMeeting() {
    //
    // }
}
