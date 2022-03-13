package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.meetingentry.IsRecurring;
import seedu.address.model.meetingentry.MeetingDateTime;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.model.meetingentry.MeetingName;
import seedu.address.model.meetingentry.MeetingUrl;
import seedu.address.model.modulecode.ModuleCode;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building MeetingEntry objects.
 */
public class MeetingEntryBuilder {

    public static final String DEFAULT_MEETINGNAME = "CS2103T Lecture";
    public static final String DEFAULT_URL = "https://legit-uni.zoom.us/j/344299221?pwd=F3a99221";
    public static final String DEFAULT_DATETIME = "13mar2022";
    public static final String DEFAULT_MODULECODE = "CS2103";
    public static final String DEFAULT_ISRECURRING = "Y";

    private MeetingName meetingName;
    private MeetingUrl meetingUrl;
    private MeetingDateTime meetingDateTime;
    private ModuleCode moduleCode;
    private IsRecurring isRecurring;
    private Set<Tag> tags;

    /**
     * Creates a {@code MeetingEntryBuilder} with the default details.
     */
    public MeetingEntryBuilder() {
        meetingName = new MeetingName(DEFAULT_MEETINGNAME);
        meetingUrl = new MeetingUrl(DEFAULT_URL);
        meetingDateTime = new MeetingDateTime(DEFAULT_DATETIME);
        moduleCode = new ModuleCode(DEFAULT_MODULECODE);
        isRecurring = new IsRecurring(DEFAULT_ISRECURRING);
        tags = new HashSet<>();
    }

    /**
     * Initializes the MeetingEntryBuilder with the data of {@code meetingEntryToCopy}.
     */
    public MeetingEntryBuilder(MeetingEntry meetingEntryToCopy) {
        meetingName = meetingEntryToCopy.getName();
        meetingUrl = meetingEntryToCopy.getUrl();
        meetingDateTime = meetingEntryToCopy.getDateTime();
        moduleCode = meetingEntryToCopy.getModuleCode();
        isRecurring = meetingEntryToCopy.getIsRecurring();
        tags = new HashSet<>(meetingEntryToCopy.getTags());
    }

    /**
     * Sets the {@code MeetingName} of the {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withName(String name) {
        this.meetingName = new MeetingName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Url} of the {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withUrl(String url) {
        this.meetingUrl = new MeetingUrl(url);
        return this;
    }

    /**
     * Sets the {@code <eetingDateTime} of the {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withDateTime(String dateTime) {
        this.meetingDateTime = new MeetingDateTime(dateTime);
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
     * Sets the {@code ModuleCode} of the {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Creates the {@code MeetingEntry} represented by this {@code MeetingEntryBuilder} instance.
     */
    public MeetingEntry build() {
        return new MeetingEntry(meetingName, meetingUrl,
                meetingDateTime, moduleCode, isRecurring, tags);
    }
}
