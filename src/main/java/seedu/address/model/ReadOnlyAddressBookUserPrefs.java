package seedu.address.model;

import java.nio.file.Path;

/**
 * Unmodifiable view of address book user prefs.
 * This interface is a temporary holding spot, and will be removed once its LinkyTime counterpart is completed.
 */
public interface ReadOnlyAddressBookUserPrefs {
    Path getAddressBookFilePath();
}
