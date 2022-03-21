package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meetingentry.FindCommand;
import seedu.address.logic.parser.meetingentry.FindCommandParser;
import seedu.address.model.meetingentry.MeetingEntryContainsKeywordsPredicate;

public class FindCommandParserTest {
    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new MeetingEntryContainsKeywordsPredicate(Arrays.asList("CS2101", "CS2030")));
        assertParseSuccess(parser, "CS2101 CS2030", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2101 \n \t CS2030  \t", expectedFindCommand);
    }
}
