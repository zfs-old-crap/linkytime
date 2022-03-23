package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.meeting.EditMeetingCommand;
import seedu.address.model.LinkyTime;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.meeting.EditMeetingDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 * This file is an adapted version of AddressBook's CommandTestUtil and is currently missing certain functions
 * due to missing implementation. Please refer to the original implementation for further reference.
 */
public class CommandTestUtil {
    public static final String VALID_NAME_LECTURE = "Lecture";
    public static final String VALID_NAME_TUTORIAL = "Tutorial";
    public static final String VALID_URL_LECTURE = "https://www.google.com";
    public static final String VALID_URL_TUTORIAL = "https://www.zoom.com";
    public static final String VALID_DATETIME_LECTURE = "Friday";
    public static final String VALID_DATETIME_TUTORIAL = "Tuesday";
    public static final String VALID_DURATION_LECTURE = "2";
    public static final String VALID_DURATION_TUTORIAL = "1.5";
    public static final String VALID_MODULE_LECTURE = "CS2103";
    public static final String VALID_MODULE_TUTORIAL = "CS2101";
    public static final String VALID_RECURRING_LECTURE = "Y";
    public static final String VALID_RECURRING_TUTORIAL = "N";
    public static final String VALID_TAG_LECTURE = "damith";
    public static final String VALID_TAG_TUTORIAL = "amy";

    public static final String NAME_DESC_LECTURE = " " + PREFIX_NAME + VALID_NAME_LECTURE;
    public static final String NAME_DESC_TUTORIAL = " " + PREFIX_NAME + VALID_NAME_TUTORIAL;
    public static final String URL_DESC_LECTURE = " " + PREFIX_URL + VALID_URL_LECTURE;
    public static final String URL_DESC_TUTORIAL = " " + PREFIX_URL + VALID_URL_TUTORIAL;
    public static final String DATETIME_DESC_LECTURE = " " + PREFIX_DATETIME + VALID_DATETIME_LECTURE;
    public static final String DATETIME_DESC_TUTORIAL = " " + PREFIX_DATETIME + VALID_DATETIME_TUTORIAL;
    public static final String DURATION_DESC_LECTURE = " " + PREFIX_DURATION + VALID_DURATION_LECTURE;
    public static final String DURATION_DESC_TUTORIAL = " " + PREFIX_DURATION + VALID_DURATION_TUTORIAL;
    public static final String MODULE_DESC_LECTURE = " " + PREFIX_MODULE + VALID_MODULE_LECTURE;
    public static final String MODULE_DESC_TUTORIAL = " " + PREFIX_MODULE + VALID_MODULE_TUTORIAL;
    public static final String RECURRING_DESC_LECTURE = " " + PREFIX_RECURRING + VALID_RECURRING_LECTURE;
    public static final String RECURRING_DESC_TUTORIAL = " " + PREFIX_RECURRING + VALID_RECURRING_TUTORIAL;
    public static final String TAG_DESC_LECTURE = " " + PREFIX_TAG + VALID_TAG_LECTURE;
    public static final String TAG_DESC_TUTORIAL = " " + PREFIX_TAG + VALID_TAG_TUTORIAL;

    //    TODO once we start validating input
    //    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    //    public static final String INVALID_URL_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    //    public static final String INVALID_DATETIME_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    //    public static final String INVALID_MODULE_DESC = " " + PREFIX_ADDRESS; // empty string not allowed
    //    public static final String INVALID_RECURRING_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tag
    //    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_DURATION_DESC = " " + PREFIX_DURATION + "two"; // letters not allowed in duration

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditMeetingCommand.EditMeetingDescriptor DESC_LECTURE;
    public static final EditMeetingCommand.EditMeetingDescriptor DESC_TUTORIAL;

    static {
        DESC_LECTURE = new EditMeetingDescriptorBuilder().withName(VALID_NAME_LECTURE)
                .withUrl(VALID_URL_LECTURE).withDateTime(VALID_DATETIME_LECTURE).withDuration(VALID_DURATION_LECTURE)
                .withModule(VALID_MODULE_LECTURE).withIsRecurring(VALID_RECURRING_LECTURE).withTags(VALID_TAG_LECTURE)
                .build();
        DESC_TUTORIAL = new EditMeetingDescriptorBuilder().withName(VALID_NAME_TUTORIAL)
                .withUrl(VALID_URL_TUTORIAL).withDateTime(VALID_DATETIME_TUTORIAL).withDuration(VALID_DURATION_TUTORIAL)
                .withModule(VALID_MODULE_TUTORIAL).withIsRecurring(VALID_RECURRING_TUTORIAL)
                .withTags(VALID_TAG_TUTORIAL).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            final CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        final CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the {@code LinkyTime}, filtered meeting list and selected meeting remained unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.

        final LinkyTime expectedLinkyTime = new LinkyTime(actualModel.getLinkyTime());
        final List<Meeting> expectedFilteredMeetingList =
                new ArrayList<>(actualModel.getFilteredMeetingList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedLinkyTime, actualModel.getLinkyTime());
        assertEquals(expectedFilteredMeetingList, actualModel.getFilteredMeetingList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the Meeting at the given {@code targetIndex} in the
     * {@code model}'s LinkyTime.
     */
    public static void showMeetingAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMeetingList().size());

        final Meeting meeting = model.getFilteredMeetingList().get(targetIndex.getZeroBased());
        model.updateFilteredMeetingList(meeting::equals);

        assertEquals(1, model.getFilteredMeetingList().size());
    }

}
