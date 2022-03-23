package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

/**
 * Adds a meeting to LinkyTime.
 */
public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to LinkyTime. "
            + "Parameters: "
            + PREFIX_NAME + "MEETING_NAME "
            + PREFIX_URL + "URL "
            + PREFIX_DATETIME + "DATETIME "
            + PREFIX_DURATION + "DURATION "
            + PREFIX_MODULE + "MODULE "
            + PREFIX_RECURRING + "IS_RECURRING "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tutorial "
            + PREFIX_URL + "https://www.zoom.com "
            + PREFIX_DATETIME + "1-4-2022 10:40am "
            + PREFIX_DURATION + "2 "
            + PREFIX_MODULE + "CS2103 "
            + PREFIX_RECURRING + "Y "
            + PREFIX_TAG + "Boring";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in LinkyTime";

    private final Meeting meetingToAdd;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */
    public AddMeetingCommand(Meeting meeting) {
        requireNonNull(meeting);
        meetingToAdd = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMeeting(meetingToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.addMeeting(meetingToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, meetingToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMeetingCommand // instanceof handles nulls
                && meetingToAdd.equals(((AddMeetingCommand) other).meetingToAdd));
    }
}
