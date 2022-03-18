package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meetingentry.MeetingEntry;

/**
 * Panel containing the list of persons.
 */
public class MeetingEntryListPanel extends UiPart<Region> {
    private static final String FXML = "MeetingEntryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<MeetingEntry> meetingEntryListView;

    /**
     * Creates a {@code MeetingEntryListPanel} with the given {@code ObservableList}.
     */
    public MeetingEntryListPanel(ObservableList<MeetingEntry> meetingList) {
        super(FXML);
        meetingEntryListView.setItems(meetingList);
        meetingEntryListView.setCellFactory(listView -> new MeetingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code MeetingEntry} using a {@code MeetingEntryCard}.
     */
    class MeetingListViewCell extends ListCell<MeetingEntry> {
        @Override
        protected void updateItem(MeetingEntry meetingEntry, boolean isEmpty) {
            super.updateItem(meetingEntry, isEmpty);

            if (isEmpty || meetingEntry == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MeetingEntryCard(meetingEntry, getIndex() + 1).getRoot());
            }
        }
    }

}
