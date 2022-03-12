package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.LinkyTime;
import seedu.address.model.meetingentry.MeetingEntry;

/**
 * A utility class containing a list of {@code MeetingEntry} objects to be used in tests
 */
public class TypicalMeetingEntries {

    public static final MeetingEntry CS2103T_LECTURE = new MeetingEntryBuilder().withName("CS2103T Lecture")
            .withUrl("https://legit-uni.zoom.us/j/344299221?pwd=F3a99221")
            .withDateTime("18mar2022")
            .withModuleCode("CS2103T")
            .withIsRecurring("Y").build();
    public static final MeetingEntry CS2101_TUTORIAL = new MeetingEntryBuilder().withName("CS2101 Tutorial")
            .withUrl("https://legit-uni.zoom.us/j/344299221?pwd=F3a99221")
            .withDateTime("19mar2022")
            .withModuleCode("CS2101")
            .withIsRecurring("Y").build();
    public static final MeetingEntry TIKTOK_REJECTION_INTERVIEW = new MeetingEntryBuilder()
            .withName("TikTok Rejection Interview")
            .withUrl("https://legit-uni.zoom.us/j/344299221?pwd=F3a99221")
            .withDateTime("20mar2022")
            .withModuleCode("Intern")
            .withIsRecurring("N").build();

    public static final String KEYWORD_MATCHING_CS2103T_LECTURE = "CS2103T";

    private TypicalMeetingEntries() {
    } // prevents instantiation

    public static LinkyTime getTypicalLinkyTime() {
        LinkyTime lt = new LinkyTime();
        for (MeetingEntry meetingEntry : getTypicalMeetingEntries()) {
            lt.addMeetingEntry(meetingEntry);
        }
        return lt;
    }

    public static List<MeetingEntry> getTypicalMeetingEntries() {
        return new ArrayList<>(Arrays.asList(CS2103T_LECTURE, CS2101_TUTORIAL, TIKTOK_REJECTION_INTERVIEW));
    }
}
