package seedu.address.model.meetingentry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meetingentry.exceptions.DuplicateMeetingEntryException;
import seedu.address.model.meetingentry.exceptions.MeetingEntryNotFoundException;

/**
 * A list of meeting entries that does not allow nulls.
 *
 * Supports a minimal set of list operations.
 */
public class MeetingEntryList implements Iterable<MeetingEntry> {

    private final ObservableList<MeetingEntry> internalList = FXCollections.observableArrayList();
    private final ObservableList<MeetingEntry> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent meeting entry as the given argument.
     */
    public boolean contains(MeetingEntry toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a meeting entry to the list.
     * The meeting entry must not already exist in the list.
     *
     * @throws NullPointerException If {@code toAdd} is null.
     */
    public void add(MeetingEntry toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMeetingEntryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the meeting entry {@code target} in the list with {@code editedMeetingEntry}.
     * {@code target} must exist in the list.
     * The meeting entry identity of {@code editedMeetingEntry} must not be the same
     * as another existing meeting entry in the list.
     */
    public void setMeetingEntry(MeetingEntry target, MeetingEntry editedMeetingEntry) {
        requireAllNonNull(target, editedMeetingEntry);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MeetingEntryNotFoundException();
        }

        if (!target.equals(editedMeetingEntry) && contains(editedMeetingEntry)) {
            throw new DuplicateMeetingEntryException();
        }

        internalList.set(index, editedMeetingEntry);
    }

    /**
     * Removes the equivalent meeting entry from the list.
     * The meeting entry must exist in the list.
     */
    public void remove(MeetingEntry toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MeetingEntryNotFoundException();
        }
    }

    public void setMeetingEntries(MeetingEntryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code meetingEntries}.
     * {@code meetingEntries} must not contain duplicate meeting entries.
     */
    public void setMeetingEntries(List<MeetingEntry> meetingEntries) {
        requireAllNonNull(meetingEntries);
        if (!meetingEntriesAreUnique(meetingEntries)) {
            throw new DuplicateMeetingEntryException();
        }

        internalList.setAll(meetingEntries);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<MeetingEntry> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<MeetingEntry> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingEntryList // instanceof handles nulls
                && internalList.equals(((MeetingEntryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code meetingEntries} contains only unique meeting entries.
     */
    private boolean meetingEntriesAreUnique(List<MeetingEntry> meetingEntries) {
        for (int i = 0; i < meetingEntries.size() - 1; i++) {
            for (int j = i + 1; j < meetingEntries.size(); j++) {
                if (meetingEntries.get(i).equals(meetingEntries.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
