package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meetingentry.MeetingEntry;

/**
 * Represents the in-memory model of the LinkyTime data.
 */
public class ModelManager extends AddressBookModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final LinkyTime linkyTime;
    private final UserPrefs userPrefs;
    private final FilteredList<MeetingEntry> filteredMeetingEntries;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyLinkyTime linkyTime) {
        super(addressBook, userPrefs);
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.linkyTime = new LinkyTime(linkyTime);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredMeetingEntries = new FilteredList<>(this.linkyTime.getMeetingEntryList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new LinkyTime());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getLinkyTimeFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setLinkyTimeFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== LinkyTime ================================================================================

    @Override
    public void setLinkyTime(ReadOnlyLinkyTime linkyTime) {
        this.linkyTime.resetData(linkyTime);
    }

    @Override
    public ReadOnlyLinkyTime getLinkyTime() {
        return linkyTime;
    }

    @Override
    public boolean hasMeetingEntry(MeetingEntry meetingEntry) {
        requireNonNull(meetingEntry);
        return linkyTime.hasMeetingEntry(meetingEntry);
    }

    @Override
    public void deleteMeetingEntry(MeetingEntry target) {
        linkyTime.removeMeetingEntry(target);
    }

    @Override
    public void addMeetingEntry(MeetingEntry meetingEntry) {
        linkyTime.addMeetingEntry(meetingEntry);
        updateFilteredMeetingEntryList(PREDICATE_SHOW_ALL_MEETING_ENTRIES);
    }

    @Override
    public void setMeetingEntry(MeetingEntry target, MeetingEntry editedMeetingEntry) {
        requireAllNonNull(target, editedMeetingEntry);

        linkyTime.setMeetingEntry(target, editedMeetingEntry);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<MeetingEntry> getFilteredMeetingEntryList() {
        return filteredMeetingEntries;
    }

    @Override
    public void updateFilteredMeetingEntryList(Predicate<MeetingEntry> predicate) {
        requireNonNull(predicate);
        filteredMeetingEntries.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        final ModelManager other = (ModelManager) obj;
        return super.equals(other)
                && userPrefs.equals(other.userPrefs)
                && filteredMeetingEntries.equals(other.filteredMeetingEntries);
    }

}
