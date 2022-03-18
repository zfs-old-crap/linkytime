package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETING_ENTRIES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetingEntries.CS2105;
import static seedu.address.testutil.TypicalMeetingEntries.CS2106;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.meetingentry.MeetingEntryContainsKeywordsPredicate;
import seedu.address.testutil.LinkyTimeBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setLinkyTimeFilePath(Paths.get("linkyTime/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setLinkyTimeFilePath(Paths.get("new/linkyTime/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setLinkyTimeFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setLinkyTimeFilePath(null));
    }

    @Test
    public void setLinkyTimeFilePath_validPath_setsLinkyTimeFilePath() {
        Path path = Paths.get("linkyTime/file/path");
        modelManager.setLinkyTimeFilePath(path);
        assertEquals(path, modelManager.getLinkyTimeFilePath());
    }

    @Test
    public void hasMeetingEntry_nullMeetingEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasMeetingEntry(null));
    }

    @Test
    public void hasMeetingEntry_meetingEntryNotInLinkyTime_returnsFalse() {
        assertFalse(modelManager.hasMeetingEntry(CS2105));
    }

    @Test
    public void hasMeetingEntry_meetingEntryLinkyTime_returnsTrue() {
        modelManager.addMeetingEntry(CS2105);
        assertTrue(modelManager.hasMeetingEntry(CS2105));
    }

    @Test
    public void getFilteredMeetingEntryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredMeetingEntryList().remove(0));
    }

    @Test
    public void equals() {
        LinkyTime linkyTime = new LinkyTimeBuilder().withEntry(CS2105).withEntry(CS2106).build();
        LinkyTime differentLinkyTime = new LinkyTime();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(userPrefs, linkyTime);
        ModelManager modelManagerCopy = new ModelManager(userPrefs, linkyTime);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different linkyTime -> returns false
        assertFalse(modelManager.equals(new ModelManager(userPrefs, differentLinkyTime)));

        // different filteredList -> returns false
        String[] keywords = {CS2105.getModuleCode().toString()};
        modelManager.updateFilteredMeetingEntryList(new MeetingEntryContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(userPrefs, linkyTime)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredMeetingEntryList(PREDICATE_SHOW_ALL_MEETING_ENTRIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setLinkyTimeFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(differentUserPrefs, linkyTime)));
    }
}
