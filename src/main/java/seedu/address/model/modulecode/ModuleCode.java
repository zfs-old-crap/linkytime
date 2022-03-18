package seedu.address.model.modulecode;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module Code in the Module list.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCode(String)}
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS =
            "Module codes should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the module code must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String code;

    /**
     * Constructs a {@code ModuleCode}.
     *
     * @param code A valid module code.
     */
    public ModuleCode(String code) {
        requireNonNull(code);
        checkArgument(isValidModuleCode(code), MESSAGE_CONSTRAINTS);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * Returns true if a given string is a valid module code.
     *
     * @param test The {@code String} to test.
     * @return True, if the {@code String} is a valid module code.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return '[' + code + ']';
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCode // instanceof handles nulls
                && code.equals(((ModuleCode) other).code)); // state check
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

}
