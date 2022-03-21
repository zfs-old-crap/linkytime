package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalMeetings.getTypicalLinkyTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.DeleteCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {
    private final Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        final Meeting meetingToDelete = model.getFilteredMeetingList()
                .get(INDEX_FIRST_MEETING.getZeroBased());
        final DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_MEETING);

        final String expectedMessage =
                String.format(DeleteCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        final ModelManager expectedModel = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        final Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        final DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        final Meeting meetingToDelete = model.getFilteredMeetingList()
                .get(INDEX_FIRST_MEETING.getZeroBased());
        final DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_MEETING);

        final String expectedMessage =
                String.format(DeleteCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        final Model expectedModel = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);
        showNoMeeting(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        final Index outOfBoundIndex = INDEX_SECOND_MEETING;
        // ensures that outOfBoundIndex is still in bounds of LinkyTime list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLinkyTime().getMeetingList().size());

        final DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_MEETING);
        final DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_MEETING);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        final DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_MEETING);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different meeting -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }


    /**
     * Updates {@code model}'s filtered list to show no meetings.
     */
    private void showNoMeeting(Model model) {
        model.updateFilteredMeetingList(p -> false);

        assertTrue(model.getFilteredMeetingList().isEmpty());
    }
}
