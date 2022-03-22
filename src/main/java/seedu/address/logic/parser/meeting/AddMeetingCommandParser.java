package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.meeting.AddMeetingCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.IsRecurring;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDateTime;
import seedu.address.model.meeting.MeetingDuration;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.MeetingUrl;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddMeetingCommand object
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddMeetingCommand
     * and returns an AddMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMeetingCommand parse(String args) throws ParseException {
        final ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_URL, PREFIX_DATETIME,
                        PREFIX_DURATION, PREFIX_MODULE, PREFIX_RECURRING, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_URL, PREFIX_DATETIME,
                PREFIX_DURATION, PREFIX_MODULE, PREFIX_RECURRING)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        final MeetingName name = ParserUtil.parseMeetingName(argMultimap.getValue(PREFIX_NAME).get());
        final MeetingUrl url = ParserUtil.parseMeetingUrl(argMultimap.getValue(PREFIX_URL).get());
        final MeetingDateTime dateTime = ParserUtil.parseMeetingDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
        final MeetingDuration duration = ParserUtil.parseMeetingDuration(argMultimap.getValue(PREFIX_DURATION).get());
        final Module module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
        final IsRecurring recurringStatus = ParserUtil.parseRecurringStatus(argMultimap
                .getValue(PREFIX_RECURRING).get());
        final Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        final Meeting meeting = new Meeting(name, url, dateTime, duration,
                module, recurringStatus, tagList);

        return new AddMeetingCommand(meeting);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
