package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.meeting.AddCommand;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;
import seedu.address.testutil.MeetingBuilder;


public class AddCommandTest {

    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_meetingAcceptedByModel_addSuccessful() throws Exception {
        final ModelStubAcceptingMeetingAdded modelStub = new ModelStubAcceptingMeetingAdded();
        final Meeting validMeeting = new MeetingBuilder().build();

        final CommandResult commandResult = new AddCommand(validMeeting).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validMeeting), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMeeting), modelStub.meetingsAdded);
    }

    @Test
    public void execute_duplicateMeeting_throwsCommandException() {
        final Meeting validMeeting = new MeetingBuilder().build();
        final AddCommand addCommand = new AddCommand(validMeeting);
        final ModelStub modelStub = new ModelStubWithMeeting(validMeeting);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_MEETING, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        final Meeting cs2103 = new MeetingBuilder().withName("CS2103").build();
        final Meeting cs2101 = new MeetingBuilder().withName("CS2101").build();
        final AddCommand addCS2103Command = new AddCommand(cs2103);
        final AddCommand addCS2101Command = new AddCommand(cs2101);

        // same object -> returns true
        assertTrue(addCS2103Command.equals(addCS2103Command));

        // same values -> returns true
        final AddCommand addCS2103CommandCopy = new AddCommand(cs2103);
        assertTrue(addCS2103Command.equals(addCS2103CommandCopy));

        // different types -> returns false
        assertFalse(addCS2103Command.equals(1));

        // null -> returns false
        assertFalse(addCS2103CommandCopy.equals(null));

        // different meeting -> returns false
        assertFalse(addCS2103Command.equals(addCS2101Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
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
        public boolean hasMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeeting(Meeting target, Meeting editedMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getFilteredMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(Module module) {
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


    /**
     * A Model stub that contains a single meeting.
     */
    private class ModelStubWithMeeting extends ModelStub {
        private final Meeting meeting;

        ModelStubWithMeeting(Meeting meeting) {
            requireNonNull(meeting);
            this.meeting = meeting;
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return this.meeting.equals(meeting);
        }

    }

    /**
     * A Model stub that always accept the Meeting being added.
     */
    private class ModelStubAcceptingMeetingAdded extends ModelStub {
        final ArrayList<Meeting> meetingsAdded = new ArrayList<>();

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.stream().anyMatch(meeting::equals);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }
    }

}
