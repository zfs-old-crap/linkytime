package seedu.address.logic.commands.meeting.sort;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Sorts and lists all upcoming meetings in LinkyTime in chronological order, starting from
 * those that are closer to the current date.
 */
public class SortMeetingChronologicalCommand extends SortMeetingCommand {
    public static final String SORT_FUNCTION_WORD = "chronological";
    public static final String MESSAGE_SUCCESS = "Sorted all meetings chronologically.";

    /**
     * Creates a SortMeetingChronologicalCommand to sort the meeting list chronologically
     */
    public SortMeetingChronologicalCommand() {
        super((o1, o2) -> {
            Module m1 = o1.getModule();
            Module m2 = o2.getModule();
            return m1.compareTo(m2);
        });
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetingList(super.sortComparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
