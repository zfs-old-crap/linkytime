package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.model.module.Module;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<MeetingEntry> PREDICATE_SHOW_ALL_MEETING_ENTRIES = unused -> true;
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    // =========== UserPrefs ===============================================================================

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' LinkyTime file path.
     */
    Path getLinkyTimeFilePath();

    /**
     * Sets the user prefs' LinkyTime file path.
     */
    void setLinkyTimeFilePath(Path linkyTimeFilePath);

    // =========== LinkyTime ===============================================================================

    /**
     * Replaces LinkyTime data with the data in {@code linkyTime}.
     */
    void setLinkyTime(ReadOnlyLinkyTime linkyTime);

    /** Returns LinkyTime */
    ReadOnlyLinkyTime getLinkyTime();

    // =========== MeetingEntry ============================================================================

    /**
     * Returns true if a meeting entry that is identical to {@code meetingEntry} exists in LinkyTime.
     */
    boolean hasMeetingEntry(MeetingEntry meetingEntry);

    /**
     * Deletes the given meeting entry.
     * The meeting entry must exist in LinkyTime.
     */
    void deleteMeetingEntry(MeetingEntry target);

    /**
     * Adds the given meeting entry.
     * {@code meetingEntry} must not already exist in LinkyTime.
     */
    void addMeetingEntry(MeetingEntry meetingEntry);

    /**
     * Replaces the given meeting entry {@code target} with {@code editedMeetingEntry}.
     * {@code target} must exist in LinkyTime.
     */
    void setMeetingEntry(MeetingEntry target, MeetingEntry editedMeetingEntry);

    // =========== Filtered MeetingEntry List Accessors ====================================================

    /** Returns an unmodifiable view of the filtered meeting entry list */
    ObservableList<MeetingEntry> getFilteredMeetingEntryList();

    /**
     * Updates the filter of the filtered meeting entry list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMeetingEntryList(Predicate<MeetingEntry> predicate);

    // =========== Module ==================================================================================

    /**
     * Returns true if a module that is identical to {@code module} exists in LinkyTime.
     */
    boolean hasModule(Module module);

    // =========== Filtered Module List Accessors ==========================================================

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);
}
