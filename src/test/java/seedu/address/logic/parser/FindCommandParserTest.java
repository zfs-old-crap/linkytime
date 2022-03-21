package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meetingentry.FindCommand;
import seedu.address.logic.parser.meetingentry.FindCommandParser;
import seedu.address.model.meetingentry.MeetingContainsAllKeywordsPredicate;

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
                new FindCommand(new MeetingContainsAllKeywordsPredicate(Arrays.asList("CS", "Tutorial")));
        assertParseSuccess(parser, "CS Tutorial", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS \n \t Tutorial  \t", expectedFindCommand);
    }
}
