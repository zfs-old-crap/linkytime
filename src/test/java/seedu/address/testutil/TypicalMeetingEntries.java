package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_TUTORIAL;
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
import seedu.address.model.meetingentry.MeetingEntry;

/**
 * A utility class containing a list of {@code MeetingEntry} objects to be used in tests.
 */
public class TypicalMeetingEntries {
    public static final MeetingEntry CS2105 = new MeetingEntryBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Thursday").withDuration("2")
            .withModuleCode("CS2105")
            .withTags("roger").build();
    public static final MeetingEntry CS2106 = new MeetingEntryBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Wednesday").withDuration("2")
            .withModuleCode("CS2106")
            .withTags("colin").build();
    public static final MeetingEntry CS2030 = new MeetingEntryBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Thursday").withDuration("2")
            .withModuleCode("CS2030")
            .withTags("ooi").build();
    public static final MeetingEntry CS2040 = new MeetingEntryBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Monday").withDuration("1")
            .withModuleCode("CS2040")
            .withTags("CKF").build();
    public static final MeetingEntry CS2100 = new MeetingEntryBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Tuesday").withDuration("15")
            .withModuleCode("CS2100")
            .withTags("aaron").build();

    public static final MeetingEntry CS2107 = new MeetingEntryBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Thursday").withDuration("1.7")
            .withModuleCode("CS2107")
            .withTags("sufatrio").build();

    // Manually added
    public static final MeetingEntry PC1221 = new MeetingEntryBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Friday").withDuration("2")
            .withModuleCode("PC1221")
            .withTags("proftay").build();

    public static final MeetingEntry CS1101S = new MeetingEntryBuilder().withName("Lecture")
            .withUrl("https://www.zoom.com").withDateTime("Thursday").withDuration("1.5")
            .withModuleCode("CS1101S")
            .withTags("hartinmenz").build();

    // Manually added - MeetingEntry's details found in {@code CommandTestUtil}
    public static final MeetingEntry CS2101 = new MeetingEntryBuilder().withName(VALID_NAME_TUTORIAL)
            .withUrl(VALID_URL_TUTORIAL).withDateTime(VALID_DATETIME_TUTORIAL).withDuration(VALID_DURATION_TUTORIAL)
            .withModuleCode(VALID_MODULE_CODE_TUTORIAL).withIsRecurring(VALID_RECURRING_TUTORIAL)
            .withTags(VALID_TAG_TUTORIAL).build();
    public static final MeetingEntry CS2103 = new MeetingEntryBuilder().withName(VALID_NAME_LECTURE)
            .withUrl(VALID_URL_LECTURE).withDateTime(VALID_DATETIME_LECTURE).withDuration(VALID_DURATION_LECTURE)
            .withModuleCode(VALID_MODULE_CODE_LECTURE).withIsRecurring(VALID_RECURRING_LECTURE)
            .withTags(VALID_TAG_LECTURE).build();

    public static final String KEYWORD_MATCHING_TUTORIAL = "Tutorial"; // A keyword that matches TUTORIAL

    private TypicalMeetingEntries() {
    } // prevents instantiation

    /**
     * Returns a {@code LinkyTime} with all the typical meeting entries.
     */
    public static LinkyTime getTypicalLinkyTime() {
        final LinkyTime lt = new LinkyTime();
        for (final MeetingEntry meetingEntry : getTypicalMeetingEntries()) {
            lt.addMeetingEntry(meetingEntry);
        }
        return lt;
    }

    public static List<MeetingEntry> getTypicalMeetingEntries() {
        return new ArrayList<>(Arrays.asList(CS2105, CS2106, CS2030, CS2040, CS2100, CS1101S));
    }
}
