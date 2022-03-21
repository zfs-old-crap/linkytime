package seedu.address.testutil.module;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_TUTORIAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.LinkyTime;
import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {
    public static final Module CS2105 = new ModuleBuilder().withModule("CS2105").build();
    public static final Module CS2106 = new ModuleBuilder().withModule("CS2106").build();
    public static final Module CS2030 = new ModuleBuilder().withModule("CS2030").build();
    public static final Module CS2040 = new ModuleBuilder().withModule("CS2040").build();
    public static final Module CS2100 = new ModuleBuilder().withModule("CS2100").build();
    public static final Module CS1101S = new ModuleBuilder().withModule("CS1101S").build();

    // Manually added
    public static final Module PC1221 = new ModuleBuilder().withModule("PC1221").build();
    public static final Module CS2107 = new ModuleBuilder().withModule("CS2107").build();

    // Manually added - Module's details found in {@code CommandTestUtil}
    public static final Module CS2103 = new ModuleBuilder().withModule(VALID_MODULE_LECTURE).build();
    public static final Module CS2101 = new ModuleBuilder().withModule(VALID_MODULE_TUTORIAL).build();

    public static final String KEYWORD_MATCHING_CS2106 = "CS2106"; // A keyword that matches CS2106

    private TypicalModules() {
    } // prevents instantiation

    /**
     * Returns a {@code LinkyTime} with all the typical modules.
     */
    public static LinkyTime getTypicalLinkyTime() {
        final LinkyTime lt = new LinkyTime();
        for (final Module module : getTypicalModules()) {
            lt.addModule(module);
        }
        return lt;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2105, CS2106, CS2030, CS2040, CS2100, CS1101S));
    }
}
