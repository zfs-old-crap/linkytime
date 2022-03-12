package seedu.address.model.meetingentry;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code MeetingEntry}'s {@code MeetingName} matches any of the keywords given.
 */
public class LinkyTimeNameContainsKeywordsPredicate implements Predicate<MeetingEntry> {
    private final List<String> keywords;

    public LinkyTimeNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(MeetingEntry meetingEntry) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(meetingEntry.getName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkyTimeNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LinkyTimeNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
