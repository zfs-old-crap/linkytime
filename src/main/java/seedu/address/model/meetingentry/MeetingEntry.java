package seedu.address.model.meetingentry;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

/**
 * Represents a MeetingEntry in the meeting entry list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class MeetingEntry {

    // Identity fields
    private final MeetingName name;
    private final MeetingUrl url;
    private final MeetingDateTime dateTime;
    private final Module module;

    // Data fields
    private final IsRecurring isRecurring;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code MeetingEntry}. Every field must be present and non-null.
     *
     * @param name          The name of the meeting entry.
     * @param url           The URL link of the meeting.
     * @param dateTime      The date and time of the meeting.
     * @param isRecurring   Whether the meeting is recurring.
     */
    public MeetingEntry(MeetingName name, MeetingUrl url, MeetingDateTime dateTime,
                        Module module, IsRecurring isRecurring, Set<Tag> tags) {
        requireAllNonNull(name, url, dateTime, isRecurring, tags);
        this.name = name;
        this.url = url;
        this.dateTime = dateTime;
        this.module = module;
        this.isRecurring = isRecurring;
        this.tags.addAll(tags);
    }

    public MeetingName getName() {
        return name;
    }

    public MeetingUrl getUrl() {
        return url;
    }

    public MeetingDateTime getDateTime() {
        return dateTime;
    }

    public Module getModule() {
        return module;
    }

    public IsRecurring getIsRecurring() {
        return isRecurring;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }


    /**
     * Returns true if both meeting entries have the same identity and data fields.
     * This defines a stronger notion of equality between two meeting entries.
     *
     * @param other The other meeting entry to compare against.
     * @return      True if both meeting entries are the same.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MeetingEntry)) {
            return false;
        }

        final MeetingEntry otherMeetingEntry = (MeetingEntry) other;
        return otherMeetingEntry.name.equals(this.name)
                && otherMeetingEntry.url.equals(this.url)
                && otherMeetingEntry.dateTime.equals(this.dateTime)
                && otherMeetingEntry.module.equals(this.module)
                && otherMeetingEntry.isRecurring.equals(this.isRecurring)
                && otherMeetingEntry.tags.equals(this.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, dateTime, module, isRecurring, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(module)
                .append(" ")
                .append(name)
                .append("; Meeting URL: ")
                .append(url)
                .append("; Date and time: ")
                .append(dateTime)
                .append("; Is recurring: ")
                .append(isRecurring);

        final Set<Tag> tags = this.tags;
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
