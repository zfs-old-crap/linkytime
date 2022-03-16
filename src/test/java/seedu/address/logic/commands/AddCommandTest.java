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
import seedu.address.logic.commands.meetingentry.AddCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.model.modulecode.ModuleCode;
import seedu.address.model.person.Person;
import seedu.address.testutil.MeetingEntryBuilder;


public class AddCommandTest {

    @Test
    public void constructor_nullMeetingEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_meetingEntryAcceptedByModel_addSuccessful() throws Exception {
        final ModelStubAcceptingMeetingEntryAdded modelStub = new ModelStubAcceptingMeetingEntryAdded();
        final MeetingEntry validMeetingEntry = new MeetingEntryBuilder().build();

        final CommandResult commandResult = new AddCommand(validMeetingEntry).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validMeetingEntry), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMeetingEntry), modelStub.meetingEntriesAdded);
    }

    @Test
    public void execute_duplicateMeetingEntry_throwsCommandException() {
        final MeetingEntry validMeetingEntry = new MeetingEntryBuilder().build();
        final AddCommand addCommand = new AddCommand(validMeetingEntry);
        final ModelStub modelStub = new ModelStubWithMeetingEntry(validMeetingEntry);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_MEETING_ENTRY, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        final MeetingEntry cs2103 = new MeetingEntryBuilder().withName("CS2103").build();
        final MeetingEntry cs2101 = new MeetingEntryBuilder().withName("CS2101").build();
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

        // different meeting entry -> returns false
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
        public boolean hasModuleCode(ModuleCode moduleCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<ModuleCode> getFilteredModuleCodeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleCodeList(Predicate<ModuleCode> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }


    /**
     * A Model stub that contains a single meetingEntry.
     */
    private class ModelStubWithMeetingEntry extends ModelStub {
        private final MeetingEntry meetingEntry;

        ModelStubWithMeetingEntry(MeetingEntry meetingEntry) {
            requireNonNull(meetingEntry);
            this.meetingEntry = meetingEntry;
        }

        @Override
        public boolean hasMeetingEntry(MeetingEntry meetingEntry) {
            requireNonNull(meetingEntry);
            return this.meetingEntry.equals(meetingEntry);
        }

    }

    /**
     * A Model stub that always accept the MeetingEntry being added.
     */
    private class ModelStubAcceptingMeetingEntryAdded extends ModelStub {
        final ArrayList<MeetingEntry> meetingEntriesAdded = new ArrayList<>();

        @Override
        public boolean hasMeetingEntry(MeetingEntry meetingEntry) {
            requireNonNull(meetingEntry);
            return meetingEntriesAdded.stream().anyMatch(meetingEntry::equals);
        }

        @Override
        public void addMeetingEntry(MeetingEntry meetingEntry) {
            requireNonNull(meetingEntry);
            meetingEntriesAdded.add(meetingEntry);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
