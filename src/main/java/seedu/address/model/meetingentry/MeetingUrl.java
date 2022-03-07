package seedu.address.model.meetingentry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Represents a MeetingEntry's URL in the meeting entry list.
 * Guarantees: immutable; is valid as declared in {@link #isValidUrl(String)}
 */
public class MeetingUrl {

    public static final String MESSAGE_CONSTRAINTS =
            "URLs should be a valid link, and it should not be blank";

    public final URL meetingUrl;

    /**
     * Constructs a {@code MeetingUrl}.
     *
     * @param url A valid URL string.
     */
    public MeetingUrl(String url) {
        requireNonNull(url);
        checkArgument(isValidUrl(url), MESSAGE_CONSTRAINTS);
        this.meetingUrl = parseUrl(url);
    }

    /**
     * Checks if a {@code String} is a valid URL.
     *
     * @param test The {@code String} to test.
     * @return True if the URL {@code String} is valid.
     */
    public static boolean isValidUrl(String test) {
        try {
            new URL(test).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Converts a URL string into a URL object.
     *
     * @param url The URL string to convert.
     * @return The URL object.
     */
    public static URL parseUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // TODO: update this once we've figured out exception handling
            return null;
        }
    }


    @Override
    public String toString() {
        return meetingUrl.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingUrl // instanceof handles nulls
                && meetingUrl.equals(((MeetingUrl) other).meetingUrl)); // state check
    }

    @Override
    public int hashCode() {
        return meetingUrl.hashCode();
    }
}
