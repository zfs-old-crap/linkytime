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
import seedu.address.model.module.Module;

/**
 * Represents the in-memory model of the LinkyTime data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final LinkyTime linkyTime;
    private final UserPrefs userPrefs;
    private final FilteredList<MeetingEntry> filteredMeetingEntries;
    private final FilteredList<Module> filteredModules;

    /**
     * Initializes a ModelManager with the given linkyTime and userPrefs.
     */
    public ModelManager(ReadOnlyLinkyTime linkyTime, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(linkyTime, userPrefs);

        logger.fine("Initializing with LinkyTime: " + linkyTime + " and user prefs " + userPrefs);
        this.linkyTime = new LinkyTime(linkyTime);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredMeetingEntries = new FilteredList<>(this.linkyTime.getMeetingEntryList());
        filteredModules = new FilteredList<>(this.linkyTime.getModuleList());
    }

    public ModelManager() {
        this(new LinkyTime(), new UserPrefs());
    }

    // =========== UserPrefs ===============================================================================

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
        return userPrefs.getLinkyTimeFilePath();
    }

    @Override
    public void setLinkyTimeFilePath(Path linkyTimeFilePath) {
        requireNonNull(linkyTimeFilePath);
        userPrefs.setLinkyTimeFilePath(linkyTimeFilePath);
    }

    // =========== LinkyTime ===============================================================================

    @Override
    public void setLinkyTime(ReadOnlyLinkyTime linkyTime) {
        this.linkyTime.resetData(linkyTime);
    }

    @Override
    public ReadOnlyLinkyTime getLinkyTime() {
        return linkyTime;
    }

    // =========== MeetingEntry ============================================================================

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

    // =========== Filtered MeetingEntry List Accessors ====================================================

    @Override
    public ObservableList<MeetingEntry> getFilteredMeetingEntryList() {
        return filteredMeetingEntries;
    }

    @Override
    public void updateFilteredMeetingEntryList(Predicate<MeetingEntry> predicate) {
        requireNonNull(predicate);
        filteredMeetingEntries.setPredicate(predicate);
    }

    // =========== Module ==================================================================================

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return linkyTime.hasModule(module);
    }

    // =========== Filtered Module List Accessors ==========================================================

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
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
        return userPrefs.equals(other.userPrefs)
                && filteredMeetingEntries.equals(other.filteredMeetingEntries)
                && filteredModules.equals(other.filteredModules);
    }

}
