package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.LinkyTime;
import seedu.address.model.Model;

/**
 * Clears the meeting list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Meeting list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setLinkyTime(new LinkyTime());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
