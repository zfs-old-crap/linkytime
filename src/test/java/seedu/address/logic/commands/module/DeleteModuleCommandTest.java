package seedu.address.logic.commands.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.typical.TypicalLinkyTime.getTypicalLinkyTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
//import seedu.address.model.module.Module;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteModuleCommand}.
 */
public class DeleteModuleCommandTest {
    private final Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());

    // TODO: Do proper test cases for modules with and without dependent meetings
    /*
    @Test
    public void execute_validIndexUnfilteredList_success() {
        final Module moduleToDelete = model.getFilteredModuleList()
                .get(INDEX_FIRST_MEETING.getZeroBased());
        final DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_MEETING);

        final String expectedMessage =
                String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete);

        final ModelManager expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);

        assertCommandSuccess(deleteModuleCommand, model, expectedMessage, expectedModel);
    }
    */

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        final Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        final DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(outOfBoundIndex);

        assertCommandFailure(deleteModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    /*
    @Test
    public void execute_validIndexFilteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MEETING);

        final Module moduleToDelete = model.getFilteredModuleList()
                .get(INDEX_FIRST_MEETING.getZeroBased());
        final DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_MEETING);

        final String expectedMessage =
                String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete);

        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);
        showNoModule(expectedModel);

        assertCommandSuccess(deleteModuleCommand, model, expectedMessage, expectedModel);
    }
    */

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        final Index outOfBoundIndex = INDEX_SECOND_MODULE;
        // ensures that outOfBoundIndex is still in bounds of LinkyTime list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLinkyTime().getModuleList().size());

        final DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(outOfBoundIndex);

        assertCommandFailure(deleteModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteModuleCommand deleteFirstCommand = new DeleteModuleCommand(INDEX_FIRST_MODULE);
        final DeleteModuleCommand deleteSecondCommand = new DeleteModuleCommand(INDEX_SECOND_MODULE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        final DeleteModuleCommand deleteFirstCommandCopy = new DeleteModuleCommand(INDEX_FIRST_MODULE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different module -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }


    /**
     * Updates {@code model}'s filtered list to show no modules.
     */
    private void showNoModule(Model model) {
        model.updateFilteredModuleList(p -> false);

        assertTrue(model.getFilteredModuleList().isEmpty());
    }
}
