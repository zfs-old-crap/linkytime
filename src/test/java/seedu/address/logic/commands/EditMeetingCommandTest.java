package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingEntryAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING_ENTRY;
import static seedu.address.testutil.TypicalMeetingEntries.getTypicalLinkyTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meetingentry.EditMeetingCommand;
import seedu.address.logic.commands.meetingentry.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.LinkyTime;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.MeetingEntryBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMeetingCommand.
 */
public class EditMeetingCommandTest {
    private final Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        final MeetingEntry editedMeeting = new MeetingEntryBuilder().build();
        final EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        final EditMeetingCommand editCommand = new EditMeetingCommand(INDEX_FIRST_MEETING_ENTRY, descriptor);

        final String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        final Model expectedModel = new ModelManager(new LinkyTime(model.getLinkyTime()), new UserPrefs());
        expectedModel.setMeetingEntry(model.getFilteredMeetingEntryList().get(0), editedMeeting);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        final Index indexLastMeeting = Index.fromOneBased(model.getFilteredMeetingEntryList().size());
        final MeetingEntry lastMeeting = model.getFilteredMeetingEntryList().get(indexLastMeeting.getZeroBased());

        final MeetingEntryBuilder meetingInList = new MeetingEntryBuilder(lastMeeting);
        final MeetingEntry editedMeeting = meetingInList.withName(VALID_NAME_LECTURE).withUrl(VALID_URL_LECTURE)
                .withTags(VALID_DATETIME_LECTURE).build();

        final EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withName(VALID_NAME_LECTURE)
                .withUrl(VALID_URL_LECTURE).withTags(VALID_DATETIME_LECTURE).build();
        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(indexLastMeeting, descriptor);

        final String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        final Model expectedModel = new ModelManager(new LinkyTime(model.getLinkyTime()), new UserPrefs());
        expectedModel.setMeetingEntry(lastMeeting, editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_MEETING_ENTRY,
                new EditMeetingDescriptor());
        final MeetingEntry editedMeeting = model.getFilteredMeetingEntryList().get(INDEX_FIRST_MEETING_ENTRY
                .getZeroBased());

        final String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        final Model expectedModel = new ModelManager(new LinkyTime(model.getLinkyTime()), new UserPrefs());

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showMeetingEntryAtIndex(model, INDEX_FIRST_MEETING_ENTRY);

        final MeetingEntry meetingInFilteredList = model.getFilteredMeetingEntryList().get(INDEX_FIRST_MEETING_ENTRY
                .getZeroBased());
        final MeetingEntry editedMeeting = new MeetingEntryBuilder(meetingInFilteredList).withName(VALID_NAME_LECTURE)
                .build();
        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_MEETING_ENTRY,
                new EditMeetingDescriptorBuilder().withName(VALID_NAME_LECTURE).build());

        final String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        final Model expectedModel = new ModelManager(new LinkyTime(model.getLinkyTime()), new UserPrefs());
        expectedModel.setMeetingEntry(model.getFilteredMeetingEntryList().get(0), editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMeetingUnfilteredList_failure() {
        final MeetingEntry firstMeeting = model.getFilteredMeetingEntryList().get(INDEX_FIRST_MEETING_ENTRY
                .getZeroBased());
        final EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(firstMeeting).build();
        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_SECOND_MEETING_ENTRY, descriptor);

        assertCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_duplicateMeetingFilteredList_failure() {
        showMeetingEntryAtIndex(model, INDEX_FIRST_MEETING_ENTRY);

        // edit meeting in filtered list into a duplicate in LinkyTime
        final MeetingEntry meetingInList = model.getLinkyTime().getMeetingEntryList().get(INDEX_SECOND_MEETING_ENTRY
                .getZeroBased());
        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_MEETING_ENTRY,
                new EditMeetingDescriptorBuilder(meetingInList).build());

        assertCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_invalidMeetingIndexUnfilteredList_failure() {
        final Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingEntryList().size() + 1);
        final EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withName(VALID_NAME_LECTURE)
                .build();
        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of LinkyTime
     */
    @Test
    public void execute_invalidMeetingIndexFilteredList_failure() {
        showMeetingEntryAtIndex(model, INDEX_FIRST_MEETING_ENTRY);
        final Index outOfBoundIndex = INDEX_SECOND_MEETING_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of LinkyTime meeting list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLinkyTime().getMeetingEntryList().size());

        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(outOfBoundIndex,
                new EditMeetingDescriptorBuilder().withName(VALID_NAME_LECTURE).build());

        assertCommandFailure(editMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditMeetingCommand standardCommand = new EditMeetingCommand(INDEX_FIRST_MEETING_ENTRY, DESC_LECTURE);

        // same values -> returns true
        final EditMeetingDescriptor copyDescriptor = new EditMeetingDescriptor(DESC_LECTURE);
        final EditMeetingCommand commandWithSameValues = new EditMeetingCommand(INDEX_FIRST_MEETING_ENTRY,
                copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMeetingCommand(INDEX_SECOND_MEETING_ENTRY, DESC_LECTURE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMeetingCommand(INDEX_FIRST_MEETING_ENTRY, DESC_TUTORIAL)));
    }
}
