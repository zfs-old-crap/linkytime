package seedu.address.testutil.module;

import seedu.address.model.module.Module;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {
    public static final String DEFAULT_MODULE = "CS2103T";

    private Module module;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        module = new Module(DEFAULT_MODULE);
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        module = moduleToCopy;
    }

    /**
     * Sets the {@code Module} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModule(String module) {
        this.module = new Module(module);
        return this;
    }

    /**
     * Creates the {@code Module} represented by this {@code ModuleBuilder} instance.
     */
    public Module build() {
        return module;
    }
}
