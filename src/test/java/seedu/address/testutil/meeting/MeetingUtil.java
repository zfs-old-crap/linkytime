package seedu.address.testutil.meeting;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;
import static seedu.address.model.meeting.MeetingDateTime.INPUT_FORMAT;

import java.time.LocalDateTime;

import seedu.address.logic.commands.meeting.AddMeetingCommand;
import seedu.address.model.meeting.Meeting;

public class MeetingUtil {
    public static String getAddMeetingCommand(Meeting meeting) {
        return AddMeetingCommand.COMMAND_WORD + " " + getMeetingDetails(meeting);
    }

    public static String getMeetingDetails(Meeting meeting) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + meeting.getName().name + " ");
        sb.append(PREFIX_URL + meeting.getUrl().toString() + " ");
        sb.append(PREFIX_DATETIME + meeting.getStartDateTime().datetime.format(INPUT_FORMAT) + " ");
        sb.append(PREFIX_DURATION + String.valueOf(meeting.getDuration().duration) + " ");
        sb.append(PREFIX_MODULE + "1" + " ");
        sb.append(PREFIX_RECURRING + meeting.getIsRecurring().toString() + " ");
        meeting.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    public static Meeting getRelativeNonRecurringMeeting(long offsetDays) {
        LocalDateTime relativeDateTime = LocalDateTime.now();
        relativeDateTime = relativeDateTime.plusDays(offsetDays);
        Meeting relativeMeeting = new MeetingBuilder()
                .withDateTime(relativeDateTime.format(INPUT_FORMAT)).withIsRecurring("N").build();
        return relativeMeeting;
    }
}
