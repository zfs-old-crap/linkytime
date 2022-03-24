package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_SORT_FUNCTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.sort.SortMeetingByModuleCommand;
import seedu.address.logic.commands.meeting.sort.SortMeetingChronologicalCommand;
import seedu.address.logic.commands.meeting.sort.SortMeetingCommand;


public class SortMeetingCommandParserTest {
    private final SortMeetingCommandParser parser = new SortMeetingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidSortFunction_throwsParseExeption() {
        // SortMeetingByModuleCommand
        SortMeetingByModuleCommand expectedSortMeetingByModuleCommand = new SortMeetingByModuleCommand();
        assertParseFailure(parser, "abcd",
                String.format(MESSAGE_UNKNOWN_SORT_FUNCTION, SortMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsPrecise_returnsValidSortMeetingCommands() {
        // SortMeetingByModuleCommand
        SortMeetingByModuleCommand expectedSortMeetingByModuleCommand = new SortMeetingByModuleCommand();
        assertParseSuccess(parser, "bymodule", expectedSortMeetingByModuleCommand);

        // SortMeetingChronologicalCommand
        SortMeetingChronologicalCommand expectedSortMeetingChronologicalCommand = new SortMeetingChronologicalCommand();
        assertParseSuccess(parser, "chronological", expectedSortMeetingChronologicalCommand);
    }

    @Test
    public void parse_validArgsUnprecise_returnsValidSortMeetingCommands() {
        // SortMeetingByModuleCommand
        SortMeetingByModuleCommand expectedSortMeetingByModuleCommand = new SortMeetingByModuleCommand();
        assertParseSuccess(parser, "bymodule        ", expectedSortMeetingByModuleCommand);

        // SortMeetingChronologicalCommand
        SortMeetingChronologicalCommand expectedSortMeetingChronologicalCommand = new SortMeetingChronologicalCommand();
        assertParseSuccess(parser, "chronological       ", expectedSortMeetingChronologicalCommand);
    }
}
