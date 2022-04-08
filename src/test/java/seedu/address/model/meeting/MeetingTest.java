package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_LECTURE;
import static seedu.address.model.meeting.MeetingDateTime.INPUT_FORMAT;
import static seedu.address.testutil.typical.TypicalMeetings.CS2103;
import static seedu.address.testutil.typical.TypicalMeetings.CS2105;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.meeting.MeetingBuilder;

public class MeetingTest {
    @Test
    public void getStartDateTime_nonRecurringMeeting_returnStartDateTime() {
        final Meeting meeting = new MeetingBuilder().withIsRecurring("N").build();
        assertEquals(meeting.getStartDateTime(), MeetingBuilder.DEFAULT_DATETIME);
    }

    @Test
    public void getStartDateTime_recurringMeeting_returnNextRecurrence() {
//        final Meeting meeting = new MeetingBuilder().withIsRecurring("Y").build();
//        assertEquals(meeting.getStartDateTime(), MeetingBuilder.DEFAULT_DATETIME);
    }

    @Test
    public void getEndDateTime_nonRecurringMeeting_returnEndDateTimeFromStartDateTime() {
        final Meeting meeting = new MeetingBuilder().withIsRecurring("N").build();
        LocalDateTime expectedEndDateTime =
                LocalDateTime.parse(MeetingBuilder.DEFAULT_DATETIME, INPUT_FORMAT);
        assertEquals(meeting.getEndDateTime(), new MeetingDateTime(expectedEndDateTime));
    }

    @Test
    public void getEndDateTime_recurringMeeting_returnEndDateTimeFromNextRecurrence() {
        //
    }

    @Test
    public void getNextRecurrence_recurringMeeting_return_nextUpcomingStartDateTime() {
        //
    }

    @Test
    public void getTags_modifySet_throwsUnsupportedOperationException() {
        final Meeting meeting = new MeetingBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> meeting.getTags().add(new Tag("tag")));
    }

    @Test
    public void equals() {
        // same values -> returns true
        final Meeting cs2105Copy = new MeetingBuilder(CS2105).build();
        assertTrue(CS2105.equals(cs2105Copy));

        // same object -> returns true
        assertTrue(CS2105.equals(CS2105));

        // null -> returns false
        assertFalse(CS2105.equals(null));

        // different type -> returns false
        assertFalse(CS2105.equals(5));

        // different meeting -> returns false
        assertFalse(CS2105.equals(CS2103));

        // different name -> returns false
        Meeting editedCs2105 = new MeetingBuilder(CS2105).withName(VALID_NAME_TUTORIAL).build();
        assertFalse(CS2105.equals(editedCs2105));

        // different url -> returns false
        editedCs2105 = new MeetingBuilder(CS2105).withUrl(VALID_URL_LECTURE).build();
        assertFalse(CS2105.equals(editedCs2105));

        // different start datetime -> returns false
        editedCs2105 = new MeetingBuilder(CS2105).withDateTime(VALID_DATETIME_TUTORIAL).build();
        assertFalse(CS2105.equals(editedCs2105));

        // different duration -> returns false
        editedCs2105 = new MeetingBuilder(CS2105).withDuration(VALID_DURATION_TUTORIAL).build();
        assertFalse(CS2105.equals(editedCs2105));

        // different module -> returns false
        editedCs2105 = new MeetingBuilder(CS2105).withModule(VALID_MODULE_TUTORIAL).build();
        assertFalse(CS2105.equals(editedCs2105));

        // different recurrence -> returns false
        editedCs2105 = new MeetingBuilder(CS2105).withIsRecurring(VALID_RECURRING_TUTORIAL).build();
        assertFalse(CS2105.equals(editedCs2105));

        // different tags -> returns false
        editedCs2105 = new MeetingBuilder(CS2105).withTags(VALID_TAG_TUTORIAL).build();
        assertFalse(CS2105.equals(editedCs2105));
    }

    // compareTo
}
