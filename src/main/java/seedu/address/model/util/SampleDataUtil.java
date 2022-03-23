package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.LinkyTime;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.meeting.IsRecurring;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDateTime;
import seedu.address.model.meeting.MeetingDuration;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.MeetingUrl;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code LinkyTime} with sample data.
 */
public class SampleDataUtil {
    public static Meeting[] getSampleMeetings() {
        return new Meeting[] {
            new Meeting(
                new MeetingName("CS2103T Lecture"),
                new MeetingUrl("https://legit-uni.zoom.us/j/344299221?pwd=F3a99221"),
                new MeetingDateTime("18-03-2022 3:00pm"),
                new MeetingDuration("2"),
                new Module("CS2103T"),
                new IsRecurring("Y"),
                getTagSet()
            ),
            new Meeting(
                new MeetingName("CS2101 Tutorial"),
                new MeetingUrl("https://meet.google.com/omg-look-ma"),
                new MeetingDateTime("19-03-2022 2:00pm"),
                new MeetingDuration("1"),
                new Module("CS2101"),
                new IsRecurring("Y"),
                getTagSet("memes")
            ),
            new Meeting(
                new MeetingName("TokTik Rejection Interview"),
                new MeetingUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ"),
                new MeetingDateTime("20-03-2022 1:30am"),
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
        for (final Meeting sampleMeeting : getSampleMeetings()) {
            sampleLinkyTime.addMeeting(sampleMeeting);
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
