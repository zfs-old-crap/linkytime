package seedu.address.model.meetingentry;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code MeetingEntry}'s {@code MeetingName}, {@code Module} and {@code Tags} together matches all
 * the keywords given.
 */
public class MeetingContainsAllKeywordsPredicate implements Predicate<MeetingEntry> {
    private final List<String> keywords;

    public MeetingContainsAllKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if a {@code MeetingEntry}'s {@code MeetingName},
     * {@code Module} and {@code Tags} together matches all the keywords.
     *
     * @param meetingEntry Meeting whose meeting name, module and tags need to be checked
     *                     to see if they contain all the keywords given.
     * @return True, if meeting entry's name, module and tags together contains all the keywords.
     */
    @Override
    public boolean test(MeetingEntry meetingEntry) {
        // no meeting matches nothing
        if (keywords.isEmpty()) {
            return false;
        }
        return keywords.stream()
                .allMatch(keyword -> meetingEntry.getName().name.toLowerCase().contains(keyword.toLowerCase())
                        || meetingEntry.getModule().code.toLowerCase().contains(keyword.toLowerCase())
                        || meetingEntry.getTags().stream()
                        .anyMatch(tag -> tag.tagName.toLowerCase().contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingContainsAllKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MeetingContainsAllKeywordsPredicate) other).keywords)); // state check
    }

}
