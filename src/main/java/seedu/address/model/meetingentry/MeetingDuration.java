package seedu.address.model.meetingentry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

/**
 * Represents a MeetingEntry's duration in the meeting entry list.
 * Guarantees: immutable;
 */
public class MeetingDuration {
    public static final String MESSAGE_CONSTRAINTS =
            "Duration should be given in hours and range from 1 to 24 inclusive";

    public final float duration;

    /**
     * Constructs a {@code MeetingDuration}
     *
     * @Param duration A valid duration.
     */
    public MeetingDuration(String duration) {
        requireNonNull(duration);
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        this.duration = Float.parseFloat(duration);
    }

    /**
     * Returns true if a given string is able to be parsed into
     * a float and the resulting float is less than or equal to 24 and non-negative.
     *
     * @param test The {@code String} to test.
     * @return True, if the {@code String} is a valid float.
     */
    public static boolean isValidDuration(String test) {
        try {
            final float durationToTest = Float.parseFloat(test);
            return durationToTest > 0 && durationToTest <= 24;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the endDateTime given the startDateTime.
     *
     * @param startDateTime The {@code LocalDateTime} to add the duration to.
     * @return The resulting LocalDateTime after adding the duration to the startDateTime.
     */
    public LocalDateTime getEndDateTime(LocalDateTime startDateTime) {
        final int durationInMinutes = (int) (duration * 60);
        return startDateTime.plusMinutes(durationInMinutes);
    }

    @Override
    public String toString() {
        return Float.toString(duration);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingDuration // instanceof handles nulls
                && duration == (((MeetingDuration) other).duration)); // state check
    }
}
