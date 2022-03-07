package seedu.address.model.meetingentry;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
                        IsRecurring isRecurring, Set<Tag> tags) {
        requireAllNonNull(name, url, dateTime, isRecurring, tags);
        this.name = name;
        this.url = url;
        this.dateTime = dateTime;
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

        MeetingEntry otherMeetingEntry = (MeetingEntry) other;
        return otherMeetingEntry.getName().equals(getName())
                && otherMeetingEntry.getUrl().equals(getUrl())
                && otherMeetingEntry.getDateTime().equals(getDateTime())
                && otherMeetingEntry.getIsRecurring().equals(getIsRecurring())
                && otherMeetingEntry.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, dateTime, isRecurring, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Meeting URL: ")
                .append(getUrl())
                .append("; Date and time: ")
                .append(getDateTime())
                .append("; Is recurring: ")
                .append(getIsRecurring());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
