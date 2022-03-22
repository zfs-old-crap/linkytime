//package seedu.address.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.TypicalPersons.CARL;
//import static seedu.address.testutil.TypicalPersons.ELLE;
//import static seedu.address.testutil.TypicalPersons.FIONA;
//import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.logic.commands.person.FindMeetingCommand;
//import seedu.address.model.LinkyTime;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.person.NameContainsKeywordsPredicate;
//
///**
// * Contains integration tests (interaction with the Model) for {@code FindMeetingCommand}.
// */
//public class FindMeetingCommandTest {
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new LinkyTime());
//    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new LinkyTime());
//
//    @Test
//    public void equals() {
//        NameContainsKeywordsPredicate firstPredicate =
//                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
//        NameContainsKeywordsPredicate secondPredicate =
//                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
//
//        FindMeetingCommand findFirstCommand = new FindMeetingCommand(firstPredicate);
//        FindMeetingCommand findSecondCommand = new FindMeetingCommand(secondPredicate);
//
//        // same object -> returns true
//        assertTrue(findFirstCommand.equals(findFirstCommand));
//
//        // same values -> returns true
//        FindMeetingCommand findFirstCommandCopy = new FindMeetingCommand(firstPredicate);
//        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(findFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(findFirstCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(findFirstCommand.equals(findSecondCommand));
//    }
//
//    @Test
//    public void execute_zeroKeywords_noPersonFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
//        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
//        FindMeetingCommand command = new FindMeetingCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
//    }
//
//    @Test
//    public void execute_multipleKeywords_multiplePersonsFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
//        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
//        FindMeetingCommand command = new FindMeetingCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
//    }
//
//    /**
//     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
//     */
//    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
//        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
//    }
//}
