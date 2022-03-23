package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingUrl;

public class OpenMeetingCommand extends Command {
    public static final String COMMAND_WORD = "open";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the meeting identified by the index number used in the "
            + "displayed meeting list in the system default browser.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Opened Meeting Entry: %1$s";

    public static final String MESSAGE_URL_UNABLE_TO_OPEN = "URL provided cannot be opened!";
    public static final String MESSAGE_SYSTEM_UNSUPPORTED = "Unsupported by user system!";
    public static final String MESSAGE_SYSTEM_PERMISSION_DENIED = "Permission denied by user system!";
    public static final String MESSAGE_SYSTEM_BROWSER_ERROR = "Unable to launch default system browser!";


    private final Index targetIndex;

    public OpenMeetingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        final List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        final Meeting meetingToOpen = lastShownList.get(targetIndex.getZeroBased());
        final MeetingUrl meetingUrlToOpen = meetingToOpen.getUrl();

        if (!Desktop.isDesktopSupported()) {
            throw new CommandException(MESSAGE_SYSTEM_UNSUPPORTED);
        }
        final Desktop desktop = Desktop.getDesktop();

        if (!desktop.isSupported(Desktop.Action.BROWSE)) {
            throw new CommandException(MESSAGE_SYSTEM_UNSUPPORTED);
        }

        requireNonNull(meetingUrlToOpen.meetingUrl);

        try {
            final URI uri = meetingUrlToOpen.meetingUrl.toURI();
            desktop.browse(uri);
        } catch (URISyntaxException ex) {
            throw new CommandException(MESSAGE_URL_UNABLE_TO_OPEN, ex);
        } catch (IOException ex) {
            throw new CommandException(MESSAGE_SYSTEM_BROWSER_ERROR, ex);
        } catch (SecurityException ex) {
            throw new CommandException(MESSAGE_SYSTEM_PERMISSION_DENIED, ex);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, meetingToOpen));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OpenMeetingCommand // instanceof handles nulls
                && targetIndex.equals(((OpenMeetingCommand) other).targetIndex));
    }
}
