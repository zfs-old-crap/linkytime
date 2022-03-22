package seedu.address.logic.commands.meeting;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.meeting.TypicalMeetings.getTypicalLinkyTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.meeting.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddMeetingCommand}.
 */
public class AddMeetingCommandIntegrationTest {

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

        assertCommandSuccess(new AddMeetingCommand(validMeeting), model,
                String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting), expectedModel);
    }


    //      TODO implement the testcase when able to retrieve meetinglist
    //    @Test
    //    public void execute_duplicateMeeting_throwsCommandException() {
    //        Meeting validMeeting = new MeetingBuilder().build();
    //        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new LinkyTime());
    //        expectedModel.addMeeting(validMeeting);
    //
    //        Meeting meetingInList = model.getLinkyTime().getMeetingList().get(0);
    //        assertCommandFailure(new seedu.address.logic.commands.meeting.AddMeetingCommand(meetingInList),
    //        expectedModel, AddMeetingCommand.MESSAGE_DUPLICATE_PERSON);
    //    }

}
