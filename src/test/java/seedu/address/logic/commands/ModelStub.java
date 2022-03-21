package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.model.module.Module;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getLinkyTimeFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setLinkyTimeFilePath(Path linkyTimeFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setLinkyTime(ReadOnlyLinkyTime linkyTime) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyLinkyTime getLinkyTime() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasMeetingEntry(MeetingEntry meetingEntry) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteMeetingEntry(MeetingEntry target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addMeetingEntry(MeetingEntry meetingEntry) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setMeetingEntry(MeetingEntry target, MeetingEntry editedMeetingEntry) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<MeetingEntry> getFilteredMeetingEntryList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredMeetingEntryList(Predicate<MeetingEntry> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasModule(Module module) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addModule(Module module) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}

