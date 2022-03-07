package seedu.address.model.meetingentry.exceptions;

/**
 * Signals that the operation will result in duplicate MeetingEntries (MeetingEntries are considered
 * duplicates if they have the same identity).
 */
public class DuplicateMeetingEntryException extends RuntimeException {
    public DuplicateMeetingEntryException() {
        super("Operation would result in duplicate meeting entries");
    }
}
