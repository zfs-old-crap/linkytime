package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.AddCommand;
import seedu.address.model.LinkyTime;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.model.person.Person;
import seedu.address.testutil.MeetingEntryBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new LinkyTime());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new LinkyTime());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_newMeetingEntry_success() {
        MeetingEntry validMeetingEntry = new MeetingEntryBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new LinkyTime());
        expectedModel.addMeetingEntry(validMeetingEntry);

        assertCommandSuccess(new seedu.address.logic.commands.meetingentry.AddCommand(validMeetingEntry), model,
                String.format(seedu.address.logic.commands.meetingentry.AddCommand.MESSAGE_SUCCESS,
                        validMeetingEntry), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
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
