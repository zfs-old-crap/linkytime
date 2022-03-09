package seedu.address.model.modulecode;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.modulecode.exceptions.DuplicateModuleCodeException;
import seedu.address.model.modulecode.exceptions.ModuleCodeNotFoundException;

/**
 * A list of module codes that enforces uniqueness between its elements and does not allow nulls.
 * A module is considered unique by comparing using {@code ModuleCode#equals(Object)}. As such, adding and updating
 * of module codes uses ModuleCode#equals(Object) for equality so as to ensure that the module being added or
 * updated is unique in terms of identity in the UniqueModuleCodeList. However, the removal of a module code uses
 * ModuleCode#equals(Object) so as to ensure that the module with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see ModuleCode#equals(Object)
 */
public class UniqueModuleCodeList implements Iterable<ModuleCode> {

    private final ObservableList<ModuleCode> internalList = FXCollections.observableArrayList();
    private final ObservableList<ModuleCode> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent module code as the given argument.
     */
    public boolean contains(ModuleCode toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a module code to the list.
     * The module must not already exist in the list.
     *
     * @throws NullPointerException If {@code toAdd} is null.
     */
    public void add(ModuleCode toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleCodeException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the module code {@code target} in the list with {@code editedModuleCode}.
     * {@code target} must exist in the list.
     * The module code identity of {@code editedModuleCode} must not be the same
     * as another existing module code in the list.
     */
    public void setModuleCode(ModuleCode target, ModuleCode editedModuleCode) {
        requireAllNonNull(target, editedModuleCode);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ModuleCodeNotFoundException();
        }

        if (!target.equals(editedModuleCode) && contains(editedModuleCode)) {
            throw new DuplicateModuleCodeException();
        }

        internalList.set(index, editedModuleCode);
    }

    /**
     * Removes the equivalent module code from the list.
     * The module code must exist in the list.
     */
    public void remove(ModuleCode toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleCodeNotFoundException();
        }
    }

    public void setModuleCodes(UniqueModuleCodeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code moduleCodes}.
     * {@code moduleCodes} must not contain duplicate module codes.
     */
    public void setModuleCodes(List<ModuleCode> moduleCodes) {
        requireAllNonNull(moduleCodes);
        if (!moduleCodesAreUnique(moduleCodes)) {
            throw new DuplicateModuleCodeException();
        }

        internalList.setAll(moduleCodes);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ModuleCode> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<ModuleCode> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueModuleCodeList // instanceof handles nulls
                && internalList.equals(((UniqueModuleCodeList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code moduleCodes} contains only unique module codes.
     */
    private boolean moduleCodesAreUnique(List<ModuleCode> moduleCodes) {
        for (int i = 0; i < moduleCodes.size() - 1; i++) {
            for (int j = i + 1; j < moduleCodes.size(); j++) {
                if (moduleCodes.get(i).equals(moduleCodes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
