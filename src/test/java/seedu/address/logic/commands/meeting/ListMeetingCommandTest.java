package seedu.address.logic.commands.meeting;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.meeting.TypicalMeetings.getTypicalLinkyTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListMeetingCommand.
 */
public class ListMeetingCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
        expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListMeetingCommand(), model, ListMeetingCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);
        assertCommandSuccess(new ListMeetingCommand(), model, ListMeetingCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
