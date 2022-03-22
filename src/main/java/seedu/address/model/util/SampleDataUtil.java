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
import seedu.address.model.modulecode.ModuleCode;
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
                new MeetingDateTime("18-03-2022 3:00pm"),
                new MeetingDuration("2"),
                new ModuleCode("CS2103T"),
                new IsRecurring("Y"),
                getTagSet()
            ),
            new MeetingEntry(
                new MeetingName("CS2101 Tutorial"),
                new MeetingUrl("https://meet.google.com/omg-look-ma"),
                new MeetingDateTime("19-03-2022 2:00pm"),
                new MeetingDuration("1"),
                new ModuleCode("CS2101"),
                new IsRecurring("Y"),
                getTagSet("memes")
            ),
            new MeetingEntry(
                new MeetingName("TokTik Rejection Interview"),
                new MeetingUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ"),
                new MeetingDateTime("20-03-2022 1:30am"),
                new MeetingDuration("1.5"),
                new ModuleCode("Intern"),
                new IsRecurring("N"),
                getTagSet("internship", "interview")
            )
        };
    }

    public static ModuleCode[] getSampleModuleCodes() {
        return new ModuleCode[] { new ModuleCode("CS2103T"), new ModuleCode("CS2101"), new ModuleCode("Intern") };
    }

    public static ReadOnlyLinkyTime getSampleLinkyTime() {
        final LinkyTime sampleLinkyTime = new LinkyTime();
        for (final MeetingEntry sampleMeetingEntry : getSampleMeetingEntries()) {
            sampleLinkyTime.addMeetingEntry(sampleMeetingEntry);
        }
        for (final ModuleCode sampleModuleCode : getSampleModuleCodes()) {
            sampleLinkyTime.addModuleCode(sampleModuleCode);
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
