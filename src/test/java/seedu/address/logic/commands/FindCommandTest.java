package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetingEntries.CS2030;
import static seedu.address.testutil.TypicalMeetingEntries.CS2040;
import static seedu.address.testutil.TypicalMeetingEntries.CS2101;
import static seedu.address.testutil.TypicalMeetingEntries.getTypicalLinkyTime;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meetingentry.FindCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meetingentry.MeetingEntryContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalLinkyTime());
    private Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalLinkyTime());

    @Test
    public void equals() {
        MeetingEntryContainsKeywordsPredicate firstPredicate =
                new MeetingEntryContainsKeywordsPredicate(Collections.singletonList("first"));
        MeetingEntryContainsKeywordsPredicate secondPredicate =
                new MeetingEntryContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different meeting -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noMeetingFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 0);
        MeetingEntryContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredMeetingEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMeetingEntryList());
    }

    @Test
    public void execute_multipleKeywords_multipleMeetingsFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 3);

        // predicate containing keywords matching meeting name, module code and tags
        MeetingEntryContainsKeywordsPredicate predicate = preparePredicate("Tutorial CS2030 CKF");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredMeetingEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2101, CS2030, CS2040), model.getFilteredMeetingEntryList());
    }

    /**
     * Parses {@code userInput} into a {@code MeetingEntryContainsKeywordsPredicate}.
     */
    private MeetingEntryContainsKeywordsPredicate preparePredicate(String userInput) {
        return new MeetingEntryContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
