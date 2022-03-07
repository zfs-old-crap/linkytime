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
    private final MeetingName meetingName;
    private final MeetingUrl meetingUrl;
    private final MeetingDateTime meetingDateTime;
    private final IsRecurring isRecurring;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code MeetingEntry}. Every field must be present and non-null.
     *
     * @param meetingName   The name of the meeting entry.
     * @param meetingUrl    The URL link of the meeting.
     * @param meetingDateTime   The date and time of the meeting.
     * @param isRecurring   Whether the meeting is recurring.
     */
    public MeetingEntry(MeetingName meetingName, MeetingUrl meetingUrl, MeetingDateTime meetingDateTime,
                        IsRecurring isRecurring, Set<Tag> tags) {
        requireAllNonNull(meetingName, meetingUrl, meetingDateTime, isRecurring, tags);
        this.meetingName = meetingName;
        this.meetingUrl = meetingUrl;
        this.meetingDateTime = meetingDateTime;
        this.isRecurring = isRecurring;
        this.tags.addAll(tags);
    }

    public MeetingName getMeetingName() {
        return meetingName;
    }

    public MeetingUrl getMeetingUrl() {
        return meetingUrl;
    }

    public MeetingDateTime getMeetingTime() {
        return meetingDateTime;
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
        return otherMeetingEntry.getMeetingName().equals(getMeetingName())
                && otherMeetingEntry.getMeetingUrl().equals(getMeetingUrl())
                && otherMeetingEntry.getMeetingTime().equals(getMeetingTime())
                && otherMeetingEntry.getIsRecurring().equals(getIsRecurring())
                && otherMeetingEntry.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingName, meetingUrl, meetingDateTime, isRecurring, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getMeetingName())
                .append("; Meeting URL: ")
                .append(getMeetingUrl())
                .append("; Date and Time: ")
                .append(getMeetingTime())
                .append("; Is Recurring: ")
                .append(getIsRecurring());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
