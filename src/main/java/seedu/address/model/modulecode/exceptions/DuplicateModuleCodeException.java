package seedu.address.model.modulecode.exceptions;

/**
 * Signals that the operation will result in duplicate ModuleCodes (ModuleCodes are considered
 * duplicates if they have the same identity).
 */
public class DuplicateModuleCodeException extends RuntimeException {
    public DuplicateModuleCodeException() {
        super("Operation would result in duplicate module codes");
    }
}
