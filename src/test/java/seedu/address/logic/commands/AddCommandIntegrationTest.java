package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetingEntries.getTypicalLinkyTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meetingentry.AddCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.testutil.MeetingEntryBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalLinkyTime());
    }

    @Test
    public void execute_newMeetingEntry_success() {
        final MeetingEntry validMeetingEntry = new MeetingEntryBuilder().build();

        final Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), model.getLinkyTime());
        expectedModel.addMeetingEntry(validMeetingEntry);

        assertCommandSuccess(new AddCommand(validMeetingEntry), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validMeetingEntry), expectedModel);
    }


    //      TODO implement the testcase when able to retrieve meetinglist
    //    @Test
    //    public void execute_duplicateMeetingEntry_throwsCommandException() {
    //        MeetingEntry validMeetingEntry = new MeetingEntryBuilder().build();
    //        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new LinkyTime());
    //        expectedModel.addMeetingEntry(validMeetingEntry);
    //
    //        MeetingEntry meetingEntryInList = model.getLinkyTime().getMeetingEntryList().get(0);
    //        assertCommandFailure(new seedu.address.logic.commands.meetingentry.AddCommand(meetingEntryInList),
    //        expectedModel, AddCommand.MESSAGE_DUPLICATE_PERSON);
    //    }

}
