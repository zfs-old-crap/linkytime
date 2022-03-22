package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetingEntries.CS1101S;
import static seedu.address.testutil.TypicalMeetingEntries.CS2030;
import static seedu.address.testutil.TypicalMeetingEntries.CS2040;
import static seedu.address.testutil.TypicalMeetingEntries.CS2100;
import static seedu.address.testutil.TypicalMeetingEntries.CS2105;
import static seedu.address.testutil.TypicalMeetingEntries.CS2106;
import static seedu.address.testutil.TypicalMeetingEntries.getTypicalLinkyTime;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meetingentry.FindCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meetingentry.MeetingContainsAllKeywordsPredicate;

public class FindCommandTest {
    private final Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalLinkyTime(), new UserPrefs());

    @Test
    public void equals() {
        MeetingContainsAllKeywordsPredicate firstPredicate =
                new MeetingContainsAllKeywordsPredicate(Collections.singletonList("first"));
        MeetingContainsAllKeywordsPredicate secondPredicate =
                new MeetingContainsAllKeywordsPredicate(Collections.singletonList("second"));

        final FindCommand findFirstCommand = new FindCommand(firstPredicate);
        final FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        final FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
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
        final String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 0);
        final MeetingContainsAllKeywordsPredicate predicate = preparePredicate(" ");
        final FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredMeetingEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMeetingEntryList());
    }

    @Test
    public void execute_multipleKeywords_multipleMeetingsFound() {
        // predicate containing keywords matching meeting name, module
        final String expectedFirstMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 6);

        final MeetingContainsAllKeywordsPredicate firstPredicate = preparePredicate("Lecture CS");
        final FindCommand firstCommand = new FindCommand(firstPredicate);

        expectedModel.updateFilteredMeetingEntryList(firstPredicate);
        assertCommandSuccess(firstCommand, model, expectedFirstMessage, expectedModel);
        assertEquals(Arrays.asList(CS2105, CS2106, CS2030, CS2040, CS2100, CS1101S),
                model.getFilteredMeetingEntryList());

        // predicate containing keywords matching meeting name, tag
        final String expectedSecondMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 1);

        final MeetingContainsAllKeywordsPredicate secondPredicate = preparePredicate("Lecture aaron");
        final FindCommand secondCommand = new FindCommand(secondPredicate);

        expectedModel.updateFilteredMeetingEntryList(secondPredicate);
        assertCommandSuccess(secondCommand, model, expectedSecondMessage, expectedModel);
        assertEquals(Arrays.asList(CS2100), model.getFilteredMeetingEntryList());

        // predicate containing keywords matching meeting module, tag
        final String expectedThirdMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 0);

        final MeetingContainsAllKeywordsPredicate thirdPredicate = preparePredicate("CS2101 colin");
        final FindCommand thirdCommand = new FindCommand(thirdPredicate);

        expectedModel.updateFilteredMeetingEntryList(thirdPredicate);
        assertCommandSuccess(thirdCommand, model, expectedThirdMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMeetingEntryList());

        // predicate containing keywords matching meeting name, module, tag
        final String expectedFourthMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 1);

        final MeetingContainsAllKeywordsPredicate fourthPredicate = preparePredicate("Lecture CS2105 roger");
        final FindCommand fourthCommand = new FindCommand(fourthPredicate);

        expectedModel.updateFilteredMeetingEntryList(fourthPredicate);
        assertCommandSuccess(fourthCommand, model, expectedFourthMessage, expectedModel);
        assertEquals(Arrays.asList(CS2105), model.getFilteredMeetingEntryList());
    }

    /**
     * Parses {@code userInput} into a {@code MeetingContainsAllKeywordsPredicate}.
     */
    private MeetingContainsAllKeywordsPredicate preparePredicate(String userInput) {
        return new MeetingContainsAllKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
