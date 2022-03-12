package seedu.address.logic.commands.meetingentry;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETING_ENTRIES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all meeting entries in the LinkyTime meeting entry list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all meetings";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetingEntryList(PREDICATE_SHOW_ALL_MEETING_ENTRIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
