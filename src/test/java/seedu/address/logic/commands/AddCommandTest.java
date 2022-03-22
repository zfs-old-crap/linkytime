package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.meetingentry.AddCommand;
import seedu.address.model.meetingentry.MeetingEntry;
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
    }

}
