package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Meeting> PREDICATE_SHOW_ALL_MEETINGS = unused -> true;
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

    // =========== Meeting ============================================================================

    /**
     * Returns true if a meeting that is identical to {@code meeting} exists in LinkyTime.
     */
    boolean hasMeeting(Meeting meeting);

    /**
     * Deletes the given meeting.
     * The meeting must exist in LinkyTime.
     */
    void deleteMeeting(Meeting target);

    /**
     * Adds the given meeting.
     * {@code meeting} must not already exist in LinkyTime.
     */
    void addMeeting(Meeting meeting);

    /**
     * Replaces the given meeting {@code target} with {@code editedMeeting}.
     * {@code target} must exist in LinkyTime.
     */
    void setMeeting(Meeting target, Meeting editedMeeting);

    // =========== Filtered Meeting List Accessors ====================================================

    /** Returns an unmodifiable view of the filtered meeting list */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Updates the filter of the filtered meeting list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMeetingList(Predicate<Meeting> predicate);

    // =========== Module ==================================================================================

    /**
     * Returns true if a module that is identical to {@code module} exists in LinkyTime.
     */
    boolean hasModule(Module module);

    /**
     * Adds the given module.
     * {@code module} must not already exist in LinkyTime.
     */
    void addModule(Module module);

    // =========== Filtered Module List Accessors ==========================================================

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);
}
