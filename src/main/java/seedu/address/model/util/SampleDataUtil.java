package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.LinkyTime;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.meetingentry.IsRecurring;
import seedu.address.model.meetingentry.MeetingDateTime;
import seedu.address.model.meetingentry.MeetingDuration;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.model.meetingentry.MeetingName;
import seedu.address.model.meetingentry.MeetingUrl;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code LinkyTime} with sample data.
 */
public class SampleDataUtil {
    public static MeetingEntry[] getSampleMeetingEntries() {
        return new MeetingEntry[] {
            new MeetingEntry(
                new MeetingName("CS2103T Lecture"),
                new MeetingUrl("https://legit-uni.zoom.us/j/344299221?pwd=F3a99221"),
                new MeetingDateTime("18mar20223pm"),
                new MeetingDuration("2"),
                new Module("CS2103T"),
                new IsRecurring("Y"),
                getTagSet()
            ),
            new MeetingEntry(
                new MeetingName("CS2101 Tutorial"),
                new MeetingUrl("https://meet.google.com/omg-look-ma"),
                new MeetingDateTime("19mar20222pm"),
                new MeetingDuration("1"),
                new Module("CS2101"),
                new IsRecurring("Y"),
                getTagSet("memes")
            ),
            new MeetingEntry(
                new MeetingName("TokTik Rejection Interview"),
                new MeetingUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ"),
                new MeetingDateTime("20mar20221am"),
                new MeetingDuration("1.5"),
                new Module("Intern"),
                new IsRecurring("N"),
                getTagSet("internship", "interview")
            )
        };
    }

    public static Module[] getSampleModules() {
        return new Module[] { new Module("CS2103T"), new Module("CS2101"), new Module("Intern") };
    }

    public static ReadOnlyLinkyTime getSampleLinkyTime() {
        final LinkyTime sampleLinkyTime = new LinkyTime();
        for (final MeetingEntry sampleMeetingEntry : getSampleMeetingEntries()) {
            sampleLinkyTime.addMeetingEntry(sampleMeetingEntry);
        }
        for (final Module sampleModule : getSampleModules()) {
            sampleLinkyTime.addModule(sampleModule);
        }
        return sampleLinkyTime;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
