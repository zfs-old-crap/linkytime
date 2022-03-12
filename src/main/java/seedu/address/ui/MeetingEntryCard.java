package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.meetingentry.MeetingEntry;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class MeetingEntryCard extends UiPart<Region> {

    private static final String FXML = "MeetingEntryCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final MeetingEntry meetingEntry;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label dateTime;
    @FXML
    private Label link;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code MeetingEntryCode} with the given {@code MeetingEntry} and index to display.
     */
    public MeetingEntryCard(MeetingEntry meetingEntry, int displayedIndex) {
        super(FXML);
        this.meetingEntry = meetingEntry;
        name.setText(meetingEntry.getName().name);
        dateTime.setText(meetingEntry.getDateTime().datetime);
        link.setText(meetingEntry.getUrl().meetingUrl.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingEntryCard)) {
            return false;
        }

        // state check
        MeetingEntryCard card = (MeetingEntryCard) other;
        return name.getText().equals(card.name.getText())
                && meetingEntry.equals(card.meetingEntry);
    }
}
