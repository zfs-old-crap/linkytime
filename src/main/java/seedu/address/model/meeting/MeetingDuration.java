package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Meeting's duration in the meeting list.
 * Guarantees: immutable;
 */
public class MeetingDuration {
    public static final String MESSAGE_CONSTRAINTS =
            "Duration should be given in the form of Xh Ym (where X is the number of hours"
            + "and Y the number of minutes), and should range from 1 minute to 24 hours inclusive";
    public static final Pattern VALIDATION_REGEX =
            Pattern.compile("^(?!$)(?:0*(?<hour>\\d{1,2}) *h *)?(?:0*(?<minute>\\d{1,2}) *m)?$");
    public final int hour;
    public final int minute;

    /**
     * Constructs a {@code MeetingDuration}
     *
     * @Param duration A valid duration.
     */
    public MeetingDuration(String duration) {
        requireNonNull(duration);
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        Matcher matcher = VALIDATION_REGEX.matcher(duration);
        matcher.matches();
        this.hour = Integer.parseInt(requireNonNullElse(matcher.group("hour"), "0"));
        this.minute = Integer.parseInt(requireNonNullElse(matcher.group("minute"), "0"));
    }

    /**
     * Returns true if a given string is able to be parsed into
     * a float and the resulting float is less than or equal to 24 and non-negative.
     *
     * @param test The {@code String} to test.
     * @return True, if the {@code String} is a valid float.
     */
    public static boolean isValidDuration(String test) {
        Matcher matcher = VALIDATION_REGEX.matcher(test);
        if (!matcher.matches()) {
            return false;
        }
        try {
            final int hour = Integer.parseInt(requireNonNullElse(matcher.group("hour"), "0"));
            final int minute = Integer.parseInt(requireNonNullElse(matcher.group("minute"), "0"));
            return (minute >= 1 || hour >= 1) && (minute < 60) && (hour < 24 || (hour == 24 && minute == 0));
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
        final int durationInMinutes = hour * 60 + minute;
        return startDateTime.plusMinutes(durationInMinutes);
    }

    @Override
    public String toString() {
        return hour + "h" + minute + "m";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingDuration // instanceof handles nulls
                && hour == ((MeetingDuration) other).hour // state check
                && minute == ((MeetingDuration) other).minute);
    }
}
