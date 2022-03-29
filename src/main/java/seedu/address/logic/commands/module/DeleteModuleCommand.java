package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;

/**
 * Deletes a module identified using its displayed index from LinkyTime.
 */
public class DeleteModuleCommand extends Command {
    public static final String COMMAND_WORD = "mdelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the module identified by the index number used in the displayed module list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";

    public static final String MESSAGE_DELETE_MODULE_RESTRICTED = "Unable to delete Module: %1$s\n"
            + "There are meetings assigned to this module.\n";

    private final Index targetIndex;

    public DeleteModuleCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        final List<Module> lastShownModuleList = model.getFilteredModuleList();

        if (targetIndex.getZeroBased() >= lastShownModuleList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        final Module moduleToDelete = lastShownModuleList.get(targetIndex.getZeroBased());
        final List<Meeting> allMeetings = model.getLinkyTime().getMeetingList();

        if (hasDependentMeetings(allMeetings, moduleToDelete)) {
            throw new CommandException(String.format(MESSAGE_DELETE_MODULE_RESTRICTED, moduleToDelete));
        }

        model.deleteModule(moduleToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteModuleCommand) other).targetIndex)); // state check
    }

    /**
     * Checks if the given module has meetings assigned to it.
     *
     * @param meetings  The list of meetings to check.
     * @param module    The module to search for.
     * @return          True, if there are dependent meetings in the list.
     */
    private boolean hasDependentMeetings(List<Meeting> meetings, Module module) {
        return meetings.stream().anyMatch(meeting -> meeting.getModule().equals(module));
    }
}
