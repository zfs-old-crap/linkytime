package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_SORT_FUNCTION;

import seedu.address.logic.commands.meeting.sort.SortMeetingByModuleCommand;
import seedu.address.logic.commands.meeting.sort.SortMeetingChronologicalCommand;
import seedu.address.logic.commands.meeting.sort.SortMeetingCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortMeetingCommand object.
 */
public class SortMeetingCommandParser implements Parser<SortMeetingCommand> {
    /**
     * Parses the give {@code String} of arguments in the context of the SortMeetingCommand and
     * returns a SortMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortMeetingCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortMeetingCommand.MESSAGE_USAGE));
        }

        String sortFunctionString = trimmedArgs.split("\\s+")[0];

        switch (sortFunctionString) {
        case SortMeetingByModuleCommand.SORT_FUNCTION_WORD:
            return new SortMeetingByModuleCommand();
        case SortMeetingChronologicalCommand.SORT_FUNCTION_WORD:
            return new SortMeetingChronologicalCommand();
        default:
            throw new ParseException(
                    String.format(MESSAGE_UNKNOWN_SORT_FUNCTION, SortMeetingCommand.MESSAGE_USAGE));
        }
    }
}
