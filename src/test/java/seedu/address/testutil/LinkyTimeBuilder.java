package seedu.address.testutil;

import seedu.address.model.LinkyTime;
import seedu.address.model.meetingentry.MeetingEntry;

/**
 * A utility class to help with building a LinkyTime objects.
 * Example usage: <br>
 * {@code LinkyTime lt = new LinkyTimeBuilder().withEntry("CS2103T_tutorial", "CS2106_lab").build();}
 */
public class LinkyTimeBuilder {
    private LinkyTime linkyTime;

    public LinkyTimeBuilder() {
        linkyTime = new LinkyTime();
    }

    public LinkyTimeBuilder(LinkyTime linkyTime) {
        this.linkyTime = linkyTime;
    }

    /**
     * Adds a new {@code MeetingEntry} to the {@code LinkyTime} that we are building.
     */
    public LinkyTimeBuilder withEntry(MeetingEntry meetingEntry) {
        linkyTime.addMeetingEntry(meetingEntry);
        return this;
    }

    public LinkyTime build() {
        return linkyTime;
    }
}
