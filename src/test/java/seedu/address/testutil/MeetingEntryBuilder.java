package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.meetingentry.IsRecurring;
import seedu.address.model.meetingentry.MeetingDateTime;
import seedu.address.model.meetingentry.MeetingDuration;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.model.meetingentry.MeetingName;
import seedu.address.model.meetingentry.MeetingUrl;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building MeetingEntry objects.
 */
public class MeetingEntryBuilder {
    public static final String DEFAULT_NAME = "CS2103T Lecture";
    public static final String DEFAULT_URL = "https://legit-uni.zoom.us/j/344299221?pwd=F3a99221";
    public static final String DEFAULT_DATETIME = "Tuesday";
    public static final String DEFAULT_DURATION = "2";
    public static final String DEFAULT_MODULE = "CS2103";
    public static final String DEFAULT_RECURRENCE = "Y";

    private MeetingName name;
    private MeetingUrl url;
    private MeetingDateTime dateTime;
    private MeetingDuration duration;
    private Module module;
    private IsRecurring isRecurring;
    private Set<Tag> tags;

    /**
     * Creates a {@code MeetingEntryBuilder} with the default details.
     */
    public MeetingEntryBuilder() {
        name = new MeetingName(DEFAULT_NAME);
        url = new MeetingUrl(DEFAULT_URL);
        dateTime = new MeetingDateTime(DEFAULT_DATETIME);
        duration = new MeetingDuration(DEFAULT_DURATION);
        module = new Module(DEFAULT_MODULE);
        isRecurring = new IsRecurring(DEFAULT_RECURRENCE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the MeetingEntryBuilder with the data of {@code meetingEntryToCopy}.
     */
    public MeetingEntryBuilder(MeetingEntry meetingEntryToCopy) {
        name = meetingEntryToCopy.getName();
        url = meetingEntryToCopy.getUrl();
        dateTime = meetingEntryToCopy.getDateTime();
        duration = meetingEntryToCopy.getDuration();
        module = meetingEntryToCopy.getModule();
        isRecurring = meetingEntryToCopy.getIsRecurring();
        tags = new HashSet<>(meetingEntryToCopy.getTags());
    }

    /**
     * Sets the {@code MeetingName} of the {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withName(String name) {
        this.name = new MeetingName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code MeetingUrl} of the {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withUrl(String url) {
        this.url = new MeetingUrl(url);
        return this;
    }

    /**
     * Sets the {@code MeetingDateTime} of the {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withDateTime(String dateTime) {
        this.dateTime = new MeetingDateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code MeetingDuration} of the {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withDuration(String duration) {
        this.duration = new MeetingDuration(duration);
        return this;
    }

    /**
     * Sets the {@code IsRecurring} field of the {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withIsRecurring(String isRecurring) {
        this.isRecurring = new IsRecurring(isRecurring);
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withModule(String module) {
        this.module = new Module(module);
        return this;
    }

    /**
     * Creates the {@code MeetingEntry} represented by this {@code MeetingEntryBuilder} instance.
     */
    public MeetingEntry build() {
        return new MeetingEntry(name, url, dateTime, duration, module, isRecurring, tags);
    }
}
