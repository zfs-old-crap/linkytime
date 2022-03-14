package seedu.address.logic.commands.meetingentry;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meetingentry.MeetingEntry;

/**
 * Adds a meeting entry to LinkyTime.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting entry to LinkyTime. "
            + "Parameters: "
            + PREFIX_NAME + "MEETING_NAME "
            + PREFIX_URL + "LINK "
            + PREFIX_DATETIME + "DATETIME "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + PREFIX_RECURRING + "IS_RECURRING "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tutorial "
            + PREFIX_URL + "https://www.zoom.com "
            + PREFIX_DATETIME + "13mar2022 "
            + PREFIX_MODULE_CODE + "CS2103 "
            + PREFIX_RECURRING + "Y "
            + PREFIX_TAG + "Boring";

    public static final String MESSAGE_SUCCESS = "New meeting entry added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING_ENTRY = "This meeting entry already exists in LinkyTime";

    private final MeetingEntry toAdd;

    /**
     * Creates an AddCommand to add the specified {@code MeetingEntry}
     */
    public AddCommand(MeetingEntry entry) {
        requireNonNull(entry);
        toAdd = entry;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMeetingEntry(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING_ENTRY);
        }

        model.addMeetingEntry(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
