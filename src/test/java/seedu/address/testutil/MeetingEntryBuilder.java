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

    public static final String DEFAULT_NAME = "Lecture";
    public static final String DEFAULT_URL = "https://www.zoom.com";
    public static final String DEFAULT_DATETIME = "Tuesday";
    public static final String DEFAULT_MODULE_CODE = "CS2101";
    public static final String DEFAULT_RECURRENCE = "Y";

    private MeetingName name;
    private MeetingUrl url;
    private MeetingDateTime dateTime;
    private ModuleCode moduleCode;
    private IsRecurring isRecurring;
    private Set<Tag> tags;

    /**
     * Creates a {@code MeetingEntryBuilder} with the default details.
     */
    public MeetingEntryBuilder() {
        name = new MeetingName(DEFAULT_NAME);
        url = new MeetingUrl(DEFAULT_URL);
        dateTime = new MeetingDateTime(DEFAULT_DATETIME);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
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
        moduleCode = meetingEntryToCopy.getModuleCode();
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
    public MeetingEntryBuilder withTags(String ... tags) {
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
     * Sets the {@code DateTime} of the {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withDateTime(String dateTime) {
        this.dateTime = new MeetingDateTime(dateTime);
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
     * Sets the {@code IsRecurring} of the {@code MeetingEntry} that we are building.
     */
    public MeetingEntryBuilder withIsRecurring(String isRecurring) {
        this.isRecurring = new IsRecurring(isRecurring);
        return this;
    }

    public MeetingEntry build() {
        return new MeetingEntry(name, url, dateTime, moduleCode, isRecurring, tags);
    }

}
