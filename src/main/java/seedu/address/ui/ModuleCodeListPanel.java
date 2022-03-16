package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.modulecode.ModuleCode;

/**
 * Panel containing the list of module codes.
 */
public class ModuleCodeListPanel extends UiPart<Region> {
    private static final String FXML = "ModuleCodeListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModuleCodeListPanel.class);

    @FXML
    private ListView<ModuleCode> moduleCodeListView;

    /**
     * Creates a {@code ModuleCodeListPanel} with the given {@code ObservableList}.
     */
    public ModuleCodeListPanel(ObservableList<ModuleCode> moduleCodeList) {
        super(FXML);
        moduleCodeListView.setItems(moduleCodeList);
        moduleCodeListView.setCellFactory(listView -> new ModuleCodeListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ModuleCode} using a {@code ModuleCodeCard}.
     */
    class ModuleCodeListViewCell extends ListCell<ModuleCode> {
        @Override
        protected void updateItem(ModuleCode moduleCode, boolean empty) {
            super.updateItem(moduleCode, empty);

            if (empty || moduleCode == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModuleCodeCard(moduleCode, getIndex() + 1).getRoot());
            }
        }
    }

}
