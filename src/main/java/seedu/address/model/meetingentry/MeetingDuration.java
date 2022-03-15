package seedu.address.model.meetingentry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Represents a MeetingEntry's duration in the meeting entry list.
 * Guarantees: immutable;
 */
public class MeetingDuration {

    public static final String MESSAGE_CONTRAINTS =
            "Duration should be given in hours and be less than 24hours";

    public final float duration;

    /**
     * Constructs a {@code MeetingDuration}
     *
     * @Param duration A valid duration.
     */
    public MeetingDuration(String duration) {
        requireNonNull(duration);
        checkArgument(isValidDuration(duration), MESSAGE_CONTRAINTS);
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
            final float number = Float.parseFloat(test);
            if (number > 24 || number < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns the endTime given the startTime.
     *
     * @param startTime The {@code LocalDateTime} to add the duration to.
     * @return The resulting time after adding the duration to the startTime.
     */
    public LocalDateTime getEndTime(LocalDateTime startTime) {
        final int hours = (int) duration;
        final float remainder = duration - hours;
        final int roundedOffMinutes = (int) (remainder * 60);
        final Duration dur = Duration.ofHours(hours).plusMinutes(roundedOffMinutes);
        return startTime.plus(dur);
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
