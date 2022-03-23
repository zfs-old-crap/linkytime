package seedu.address.logic.commands.meeting.sort;

import java.util.Comparator;

import seedu.address.logic.commands.Command;
import seedu.address.model.meeting.Meeting;

/**
 * Sorts and lists all upcoming meetings in LinkyTime according to a sort function provided.
 */
public abstract class SortMeetingCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all upcoming meetings according to "
            + "the sort function provided(case-insensitive). For a list of all the sort functions, please refer "
            + "to our user guide at https://ay2122s2-cs2103t-t13-3.github.io/tp/UserGuide.html.\n"
            + "Parameters: SORT_FUNCTION\n"
            + "Example: " + COMMAND_WORD + " bymodule";

    protected final Comparator<Meeting> sortComparator;

    public SortMeetingCommand(Comparator<Meeting> sortComparator) {
        this.sortComparator = sortComparator;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortMeetingCommand // instanceof handles nulls
                && sortComparator.equals(((SortMeetingCommand) other).sortComparator)); // state check
    }
}
