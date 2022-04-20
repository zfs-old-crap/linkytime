package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MeetingDurationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingDuration(null));
    }

    @Test
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        String invalidDuration = "";
        assertThrows(IllegalArgumentException.class, () -> new MeetingDuration(invalidDuration));
    }

    @Test
    public void isValidDuration() {
        // null duration
        assertThrows(NullPointerException.class, ()->MeetingDuration.isValidDuration(null));

        // blank duration
        assertFalse(MeetingDuration.isValidDuration(""));
        assertFalse(MeetingDuration.isValidDuration("  "));

        // wrong format
        assertFalse(MeetingDuration.isValidDuration("12"));
        assertFalse(MeetingDuration.isValidDuration("h"));
        assertFalse(MeetingDuration.isValidDuration("h5m"));

        // has decimals
        assertFalse(MeetingDuration.isValidDuration("1.2h"));
        assertFalse(MeetingDuration.isValidDuration("15.5m"));
        assertFalse(MeetingDuration.isValidDuration("1h1.5m"));
        assertFalse(MeetingDuration.isValidDuration("2.2h5m"));

        // has spaces
        assertTrue(MeetingDuration.isValidDuration("0h  5m"));
        assertTrue(MeetingDuration.isValidDuration("0 h5m"));
        assertTrue(MeetingDuration.isValidDuration("0h5 m"));
        assertTrue(MeetingDuration.isValidDuration("0  h  5  m"));

        // EPs [-INFINITY ... 0], [1 ... 24/60], [25/61 ... INFINITY]
        // EP1
        assertFalse(MeetingDuration.isValidDuration(String.valueOf(Integer.MIN_VALUE) + "h"));
        assertFalse(MeetingDuration.isValidDuration(String.valueOf(Integer.MIN_VALUE) + "m"));
        assertFalse(MeetingDuration.isValidDuration(5 + "h" + String.valueOf(Integer.MIN_VALUE) + "m"));
        assertFalse(MeetingDuration.isValidDuration(String.valueOf(Integer.MIN_VALUE) + "h" + 5 + "m"));
        assertFalse(MeetingDuration.isValidDuration("-12512h"));
        assertFalse(MeetingDuration.isValidDuration("-12512m"));
        assertFalse(MeetingDuration.isValidDuration("-155h20m"));
        assertFalse(MeetingDuration.isValidDuration("5h-155m"));
        assertFalse(MeetingDuration.isValidDuration("0h"));
        assertFalse(MeetingDuration.isValidDuration("0m"));
        assertFalse(MeetingDuration.isValidDuration("0h0m"));


        // EP2
        assertTrue(MeetingDuration.isValidDuration("1h"));
        assertTrue(MeetingDuration.isValidDuration("1m"));
        assertTrue(MeetingDuration.isValidDuration("0h1m"));
        assertTrue(MeetingDuration.isValidDuration("1h0m"));
        assertTrue(MeetingDuration.isValidDuration("2h"));
        assertTrue(MeetingDuration.isValidDuration("2m"));
        assertTrue(MeetingDuration.isValidDuration("000000h1m"));
        assertTrue(MeetingDuration.isValidDuration("000001h0m"));
        assertTrue(MeetingDuration.isValidDuration("1h000000m"));
        assertTrue(MeetingDuration.isValidDuration("0h000001m"));
        assertTrue(MeetingDuration.isValidDuration("59m"));
        assertTrue(MeetingDuration.isValidDuration("23h59m"));
        assertTrue(MeetingDuration.isValidDuration("24h"));
        assertTrue(MeetingDuration.isValidDuration("24h0m"));

        // EP3
        assertFalse(MeetingDuration.isValidDuration("25h"));
        assertFalse(MeetingDuration.isValidDuration("24h1m"));
        assertFalse(MeetingDuration.isValidDuration("60m"));
        assertFalse(MeetingDuration.isValidDuration("1234h"));
        assertFalse(MeetingDuration.isValidDuration("1234m"));
        assertFalse(MeetingDuration.isValidDuration("1234h50m"));
        assertFalse(MeetingDuration.isValidDuration("12h5051m"));
        assertFalse(MeetingDuration.isValidDuration("0h60m"));
        assertFalse(MeetingDuration.isValidDuration("25h0m"));
        assertFalse(MeetingDuration.isValidDuration(String.valueOf(Integer.MAX_VALUE) + "h"));
        assertFalse(MeetingDuration.isValidDuration(String.valueOf(Integer.MAX_VALUE) + "m"));
        assertFalse(MeetingDuration.isValidDuration(5 + "h" + String.valueOf(Integer.MAX_VALUE) + "m"));
        assertFalse(MeetingDuration.isValidDuration(String.valueOf(Integer.MAX_VALUE) + "h" + 5 + "m"));
    }
}
