package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;

import seedu.address.logic.commands.meetingentry.AddCommand;
import seedu.address.model.meetingentry.MeetingEntry;

/**
 * This class is a placeholder for MeetingUtil. For reference please refer to the obsolete package.
 */
public class MeetingUtil {
    public static String getAddCommand(MeetingEntry meetingEntry) {
        return AddCommand.COMMAND_WORD + " " + getMeetingDetails(meetingEntry);
    }

    public static String getMeetingDetails(MeetingEntry meetingEntry) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + meetingEntry.getName().name + " ");
        sb.append(PREFIX_URL + meetingEntry.getUrl().toString() + " ");
        sb.append(PREFIX_DATETIME + meetingEntry.getDateTime().toString() + " ");
        sb.append(PREFIX_DURATION + String.valueOf(meetingEntry.getDuration().duration) + " ");
        sb.append(PREFIX_MODULE_CODE + meetingEntry.getModuleCode().code + " ");
        sb.append(PREFIX_RECURRING + meetingEntry.getIsRecurring().toString() + " ");
        meetingEntry.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
}
