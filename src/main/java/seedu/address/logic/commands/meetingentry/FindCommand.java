package seedu.address.logic.commands.meetingentry;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.meetingentry.MeetingEntryContainsKeywordsPredicate;

/**
 * Finds and lists all meetings in LinkyTime whose name, module code or tags contain any
 * of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meeting entries whose names, module codes "
            + "or tags contain any of the specified keywords (case-insensitive) and "
            + "displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2101 cs2103t tutorial";

    private final MeetingEntryContainsKeywordsPredicate predicate;

    public FindCommand(MeetingEntryContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetingEntryList(predicate);
        return new CommandResult(String.format(
                Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, model.getFilteredMeetingEntryList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
