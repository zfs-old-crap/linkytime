package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_TUTORIAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.LinkyTime;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalMeetings {
    public static final Meeting CS2105 = new MeetingBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Thursday").withDuration("2")
            .withModule("CS2105")
            .withTags("roger").build();
    public static final Meeting CS2106 = new MeetingBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Wednesday").withDuration("2")
            .withModule("CS2106")
            .withTags("colin").build();
    public static final Meeting CS2030 = new MeetingBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Thursday").withDuration("2")
            .withModule("CS2030")
            .withTags("ooi").build();
    public static final Meeting CS2040 = new MeetingBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Monday").withDuration("1")
            .withModule("CS2040")
            .withTags("CKF").build();
    public static final Meeting CS2100 = new MeetingBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Tuesday").withDuration("15")
            .withModule("CS2100")
            .withTags("aaron").build();

    public static final Meeting CS2107 = new MeetingBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Thursday").withDuration("1.7")
            .withModule("CS2107")
            .withTags("sufatrio").build();

    // Manually added
    public static final Meeting PC1221 = new MeetingBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Friday").withDuration("2")
            .withModule("PC1221")
            .withTags("proftay").build();

    public static final Meeting CS1101S = new MeetingBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Thursday").withDuration("1.5")
            .withModule("CS1101S")
            .withTags("hartinmenz").build();

    // Manually added - Meeting's details found in {@code CommandTestUtil}
    public static final Meeting CS2101 = new MeetingBuilder().withName(VALID_NAME_TUTORIAL)
            .withUrl(VALID_URL_TUTORIAL).withDateTime(VALID_DATETIME_TUTORIAL).withDuration(VALID_DURATION_TUTORIAL)
            .withModule(VALID_MODULE_TUTORIAL).withIsRecurring(VALID_RECURRING_TUTORIAL)
            .withTags(VALID_TAG_TUTORIAL).build();
    public static final Meeting CS2103 = new MeetingBuilder().withName(VALID_NAME_LECTURE)
            .withUrl(VALID_URL_LECTURE).withDateTime(VALID_DATETIME_LECTURE).withDuration(VALID_DURATION_LECTURE)
            .withModule(VALID_MODULE_LECTURE).withIsRecurring(VALID_RECURRING_LECTURE)
            .withTags(VALID_TAG_LECTURE).build();

    public static final String KEYWORD_MATCHING_TUTORIAL = "Tutorial"; // A keyword that matches TUTORIAL

    private TypicalMeetings() {
    } // prevents instantiation

    /**
     * Returns a {@code LinkyTime} with all the typical meetings.
     */
    public static LinkyTime getTypicalLinkyTime() {
        final LinkyTime lt = new LinkyTime();
        for (final Meeting meeting : getTypicalMeetings()) {
            lt.addMeeting(meeting);
        }
        return lt;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(CS2105, CS2106, CS2030, CS2040, CS2100, CS1101S));
    }
}
