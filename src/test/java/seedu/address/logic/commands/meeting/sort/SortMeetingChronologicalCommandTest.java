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
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.meeting.MeetingBuilder;

public class SortMeetingChronologicalCommandTest {

    @Test
    public void equals() {
        SortMeetingChronologicalCommand s1 = new SortMeetingChronologicalCommand();
        SortMeetingChronologicalCommand s2 = new SortMeetingChronologicalCommand();
        assertEquals(s1, s2);
    }

    @Test
    public void execute_unsortedMeetings_successful() {
        Model model = new ModelManager(getUnsortedLinkyTime(), new UserPrefs());
        Model expectedModel = new ModelManager(getSortedLinkyTime(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(SortMeetingChronologicalCommand.MESSAGE_SUCCESS);

        assertCommandSuccess(new SortMeetingChronologicalCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_sortedMeetings_successful() {
        Model model = new ModelManager(getSortedLinkyTime(), new UserPrefs());
        Model expectedModel = new ModelManager(getSortedLinkyTime(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(SortMeetingChronologicalCommand.MESSAGE_SUCCESS);

        assertCommandSuccess(new SortMeetingChronologicalCommand(), model, expectedCommandResult, expectedModel);
    }

    private ReadOnlyLinkyTime getUnsortedLinkyTime() {
        LinkyTime linkyTime = new LinkyTime();
        Meeting modifiedCS2101 = new MeetingBuilder(CS2101)
                .withDateTime("30-03-2022 1004").withIsRecurring("N").build();
        linkyTime.addMeeting(modifiedCS2101);
        Meeting modifiedCS1101S = new MeetingBuilder(CS1101S)
                .withDateTime("29-03-2022 1200").withIsRecurring("N").build();
        linkyTime.addMeeting(modifiedCS1101S);
        Meeting modifiedCS2103 = new MeetingBuilder(CS2103)
                .withDateTime("29-04-2022 1300").withIsRecurring("N").build();
        linkyTime.addMeeting(modifiedCS2103);
        Meeting modifiedCS2106 = new MeetingBuilder(CS2106)
                .withDateTime("01-03-2022 1400").withIsRecurring("N").build();
        linkyTime.addMeeting(modifiedCS2106);
        Meeting modifiedCS2105 = new MeetingBuilder(CS2105)
                .withDateTime("29-03-2022 1400").withIsRecurring("N").build();
        linkyTime.addMeeting(modifiedCS2105);
        return linkyTime;
    }

    private ReadOnlyLinkyTime getSortedLinkyTime() {
        LinkyTime linkyTime = new LinkyTime();
        Meeting modifiedCS2106 = new MeetingBuilder(CS2106)
                .withDateTime("01-03-2022 1400").withIsRecurring("N").build();
        linkyTime.addMeeting(modifiedCS2106);
        Meeting modifiedCS1101S = new MeetingBuilder(CS1101S)
                .withDateTime("29-03-2022 1200").withIsRecurring("N").build();
        linkyTime.addMeeting(modifiedCS1101S);
        Meeting modifiedCS2105 = new MeetingBuilder(CS2105)
                .withDateTime("29-03-2022 1400").withIsRecurring("N").build();
        linkyTime.addMeeting(modifiedCS2105);
        Meeting modifiedCS2101 = new MeetingBuilder(CS2101)
                .withDateTime("30-03-2022 1004").withIsRecurring("N").build();
        linkyTime.addMeeting(modifiedCS2101);
        Meeting modifiedCS2103 = new MeetingBuilder(CS2103)
                .withDateTime("29-04-2022 1300").withIsRecurring("N").build();
        linkyTime.addMeeting(modifiedCS2103);
        return linkyTime;
    }
}
