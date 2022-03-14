package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.meetingentry.MeetingEntry;

import java.util.Comparator;

/**
 * An UI component that displays information of a {@code MeetingEntry}.
 */
public class MeetingEntryCard extends UiPart<Region> {

    private static final String FXML = "MeetingEntryListCard.fxml";

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
    private Label id;
    @FXML
    private Label moduleCode;
    @FXML
    private Label name;
    @FXML
    private Label dateTime;
    @FXML
    private Label url;
    @FXML
    private Label isRecurring;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code MeetingEntryCode} with the given {@code MeetingEntry} and index to display.
     */
    public MeetingEntryCard(MeetingEntry meetingEntry, int displayedIndex) {
        super(FXML);
        this.meetingEntry = meetingEntry;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(meetingEntry.getModuleCode().moduleCode);
        name.setText(meetingEntry.getName().name);
        dateTime.setText(meetingEntry.getDateTime().datetime);
        url.setText(meetingEntry.getUrl().meetingUrl.toString());
        isRecurring.setVisible(meetingEntry.getIsRecurring().isRecurring);
        if (meetingEntry.getIsRecurring().isRecurring) {
            isRecurring.setText("Recurring");
        }
        isRecurring.managedProperty().bind(isRecurring.visibleProperty());
        meetingEntry.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
        final MeetingEntryCard card = (MeetingEntryCard) other;
        return id.getText().equals(card.id.getText())
                && meetingEntry.equals(card.meetingEntry);
    }
}
