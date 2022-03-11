package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.LinkyTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends AddressBookLogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final LinkyTimeParser linkyTimeParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        super(model, storage);
        this.model = model;
        this.storage = storage;
        linkyTimeParser = new LinkyTimeParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        // Try to execute the parser for LinkyTime. If it fails, execute the parser for AddressBook.
        // This is a temporary patch to allow the AddressBook parser/logic/command test cases to pass while the
        // current implementation transitions to LinkyTime's parser/logic/commands.

        // Meanwhile, it's advisable to transition the existing test cases to the equivalent LinkyTime implementation.
        // E.g., adding `ListCommand` to the LinkyTime parser would prevent the equivalent `ListCommand` from the
        // AddressBook parser from executing. As such, the output could be different and the existing `ListCommand` test
        // cases would fail. TLDR, when you add a new command for the LinkyTime parser that already exists for the
        // AddressBook parser, modify and repurpose the test cases affected to support the LinkyTime version of the
        // command instead. Eventually, AddressBook operations would no longer be used and can be safely removed.
        final CommandResult commandResult;
        try {
            final Command command = linkyTimeParser.parseCommand(commandText);
            commandResult = command.execute(model);
        } catch (Exception ex) {
            return super.execute(commandText);
        }

        try {
            storage.saveLinkyTime(model.getLinkyTime());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyLinkyTime getLinkyTime() {
        return model.getLinkyTime();
    }

    @Override
    public ObservableList<MeetingEntry> getFilteredMeetingEntryList() {
        return model.getFilteredMeetingEntryList();
    }

    @Override
    public Path getLinkyTimeFilePath() {
        return model.getLinkyTimeFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
