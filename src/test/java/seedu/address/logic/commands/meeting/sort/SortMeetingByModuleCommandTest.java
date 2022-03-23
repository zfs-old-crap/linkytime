package seedu.address.logic.commands.meeting.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.typical.TypicalMeetings.CS1101S;
import static seedu.address.testutil.typical.TypicalMeetings.CS2101;
import static seedu.address.testutil.typical.TypicalMeetings.CS2103;
import static seedu.address.testutil.typical.TypicalMeetings.CS2105;
import static seedu.address.testutil.typical.TypicalMeetings.CS2106;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.LinkyTime;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.UserPrefs;

public class SortMeetingByModuleCommandTest {

    @Test
    public void equals() {
        SortMeetingByModuleCommand s1 = new SortMeetingByModuleCommand();
        SortMeetingByModuleCommand s2 = new SortMeetingByModuleCommand();
        assertEquals(s1, s2);
    }

    @Test
    public void execute_unsortedMeetings_successful() {
        Model model = new ModelManager(getUnsortedLinkyTime(), new UserPrefs());
        Model expectedModel = new ModelManager(getSortedLinkyTime(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(SortMeetingByModuleCommand.MESSAGE_SUCCESS);

        assertCommandSuccess(new SortMeetingByModuleCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_sortedMeetings_successful() {
        Model model = new ModelManager(getSortedLinkyTime(), new UserPrefs());
        Model expectedModel = new ModelManager(getSortedLinkyTime(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(SortMeetingByModuleCommand.MESSAGE_SUCCESS);

        assertCommandSuccess(new SortMeetingByModuleCommand(), model, expectedCommandResult, expectedModel);
    }

    private ReadOnlyLinkyTime getUnsortedLinkyTime() {
        LinkyTime linkyTime = new LinkyTime();
        linkyTime.addMeeting(CS2101);
        linkyTime.addMeeting(CS1101S);
        linkyTime.addMeeting(CS2103);
        linkyTime.addMeeting(CS2106);
        linkyTime.addMeeting(CS2105);
        return linkyTime;
    }

    private ReadOnlyLinkyTime getSortedLinkyTime() {
        LinkyTime linkyTime = new LinkyTime();
        linkyTime.addMeeting(CS1101S);
        linkyTime.addMeeting(CS2101);
        linkyTime.addMeeting(CS2103);
        linkyTime.addMeeting(CS2105);
        linkyTime.addMeeting(CS2106);
        return linkyTime;
    }


}
