package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.modulecode.ModuleCode;

/**
 * An UI component that displays information of a {@code ModuleCodeCard}.
 */
public class ModuleCodeCard extends UiPart<Region> {
    private static final String FXML = "ModuleCodeListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final ModuleCode moduleCode;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label code;

    /**
     * Creates a {@code ModuleCodeCard} with the given {@code ModuleCode} and index to display.
     */
    public ModuleCodeCard(ModuleCode moduleCode, int displayedIndex) {
        super(FXML);
        this.moduleCode = moduleCode;
        id.setText(displayedIndex + ". ");
        code.setText(moduleCode.getCode());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCodeCard)) {
            return false;
        }

        // state check
        final ModuleCodeCard card = (ModuleCodeCard) other;
        return id.getText().equals(card.id.getText())
                && moduleCode.equals(card.moduleCode);
    }
}
