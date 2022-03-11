package seedu.address.model.meetingentry;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code MeetingEntry}'s {@code MeetingName}, {@code ModuleCode} or {@code Tags} matches any
 * of the keywords given.
 */
public class MeetingEntryContainsKeywordsPredicate implements Predicate<MeetingEntry> {
    private final List<String> keywords;

    public MeetingEntryContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if a {@code MeetingEntry}'s {@code MeetingName} or {@code Tags} matches any of the keywords.
     *
     * @param meetingEntry Meeting entry whose meeting name and tags need to be checked
     *                     to see if they contain any of the keywords given.
     * @return True, if meeting entry's name or tags contains any of the keywords.
     */
    @Override
    public boolean test(MeetingEntry meetingEntry) {
        return keywords.stream()
                .anyMatch(keyword -> meetingEntry.getName().name.toLowerCase().contains(keyword.toLowerCase())
                        || meetingEntry.getModuleCode().moduleCode.toLowerCase().contains(keyword.toLowerCase())
                        || meetingEntry.getTags().stream()
                        .anyMatch(tag -> tag.tagName.toLowerCase().contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingEntryContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MeetingEntryContainsKeywordsPredicate) other).keywords)); // state check
    }

}
