package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Represents Address Book User's preferences.
 * This class is a temporary holding spot, and will be removed once its LinkyTime counterpart is completed.
 */
public class AddressBookUserPrefs implements ReadOnlyAddressBookUserPrefs {
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");

    @Override
    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AddressBookUserPrefs)) { //this handles null as well.
            return false;
        }

        final AddressBookUserPrefs o = (AddressBookUserPrefs) other;

        return addressBookFilePath.equals(o.addressBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressBookFilePath);
    }

    @Override
    public String toString() {
        return "\nLocal data file location : " + addressBookFilePath;
    }
}
