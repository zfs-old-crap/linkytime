package seedu.address.logic.commands.modulecode;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULE_CODES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all module codes in the LinkyTime module code list to the user.
 */
public class ListModuleCodeCommand extends Command {
    public static final String COMMAND_WORD = "mlist";

    public static final String MESSAGE_SUCCESS = "Listed all module codes";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleCodeList(PREDICATE_SHOW_ALL_MODULE_CODES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
