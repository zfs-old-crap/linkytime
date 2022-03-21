package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.getTypicalLinkyTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.AddCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
    }

    @Test
    public void execute_newMeeting_success() {
        final Meeting validMeeting = new MeetingBuilder().build();

        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        expectedModel.addMeeting(validMeeting);

        assertCommandSuccess(new AddCommand(validMeeting), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validMeeting), expectedModel);
    }


    //      TODO implement the testcase when able to retrieve meetinglist
    //    @Test
    //    public void execute_duplicateMeeting_throwsCommandException() {
    //        Meeting validMeeting = new MeetingBuilder().build();
    //        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new LinkyTime());
    //        expectedModel.addMeeting(validMeeting);
    //
    //        Meeting meetingInList = model.getLinkyTime().getMeetingList().get(0);
    //        assertCommandFailure(new seedu.address.logic.commands.meeting.AddCommand(meetingInList),
    //        expectedModel, AddCommand.MESSAGE_DUPLICATE_PERSON);
    //    }

}
