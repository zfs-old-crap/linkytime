package seedu.address.model.meetingentry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a MeetingEntry's start date and time in the meeting entry list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class MeetingDateTime {

    //TODO: update to LocalDateTime in v1.3

    public static final String MESSAGE_CONSTRAINTS =
            "DateTime should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String datetime;


    /**
     * Constructs a {@code MeetingDateTime}.
     *
     * @param datetime A valid datetime.
     */
    public MeetingDateTime(String datetime) {
        requireNonNull(datetime);
        checkArgument(isValidDateTime(datetime), MESSAGE_CONSTRAINTS);
        this.datetime = datetime;
    }

    /**
     * Returns true if a given string is a valid datetime.
     *
     * @param test The {@code String} to test.
     * @return True, if the {@code String} is a valid datetime.
     */
    public static boolean isValidDateTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return datetime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingDateTime // instanceof handles nulls
                && datetime.equals(((MeetingDateTime) other).datetime)); // state check
    }

    @Override
    public int hashCode() {
        return datetime.hashCode();
    }

}
