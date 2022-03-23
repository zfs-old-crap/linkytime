package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RECURRING_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.RECURRING_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.URL_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.URL_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_LECTURE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.typical.TypicalMeetings.CS2101;
import static seedu.address.testutil.typical.TypicalMeetings.CS2103;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.AddMeetingCommand;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.meeting.MeetingBuilder;

public class AddMeetingCommandParserTest {
    private final AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        final Meeting expectedMeeting = new MeetingBuilder(CS2103).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_LECTURE + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, new AddMeetingCommand(expectedMeeting));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_TUTORIAL + NAME_DESC_LECTURE + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, new AddMeetingCommand(expectedMeeting));

        // multiple urls - last url accepted
        assertParseSuccess(parser, NAME_DESC_LECTURE + URL_DESC_TUTORIAL + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, new AddMeetingCommand(expectedMeeting));

        // multiple datetime - last datetime accepted
        assertParseSuccess(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_TUTORIAL
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, new AddMeetingCommand(expectedMeeting));

        // multiple duration - last duration accepted
        assertParseSuccess(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                + DURATION_DESC_TUTORIAL + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, new AddMeetingCommand(expectedMeeting));

        // multiple module - last module accepted
        assertParseSuccess(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                + DURATION_DESC_LECTURE + MODULE_DESC_TUTORIAL + MODULE_DESC_LECTURE + RECURRING_DESC_LECTURE
                + TAG_DESC_LECTURE, new AddMeetingCommand(expectedMeeting));

        // multiple recurrence - last recurrence accepted
        assertParseSuccess(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE + RECURRING_DESC_TUTORIAL + RECURRING_DESC_LECTURE
                + TAG_DESC_LECTURE, new AddMeetingCommand(expectedMeeting));

        // multiple tags - all accepted
        final Meeting expectedMeetingMultipleTags = new MeetingBuilder(CS2103)
                .withTags(VALID_TAG_LECTURE, VALID_TAG_TUTORIAL).build();
        assertParseSuccess(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE + RECURRING_DESC_LECTURE + TAG_DESC_TUTORIAL
                + TAG_DESC_LECTURE, new AddMeetingCommand(expectedMeetingMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        final Meeting expectedMeeting = new MeetingBuilder(CS2101).withTags().build();
        assertParseSuccess(parser, NAME_DESC_TUTORIAL + URL_DESC_TUTORIAL + DATETIME_DESC_TUTORIAL
                        + DURATION_DESC_TUTORIAL + MODULE_DESC_TUTORIAL + RECURRING_DESC_TUTORIAL,
                new AddMeetingCommand(expectedMeeting));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        final String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                        + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE + RECURRING_DESC_LECTURE,
                expectedMessage);

        // missing url prefix
        assertParseFailure(parser, NAME_DESC_LECTURE + VALID_URL_LECTURE + DATETIME_DESC_LECTURE
                        + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE + RECURRING_DESC_LECTURE,
                expectedMessage);

        // missing datetime prefix
        assertParseFailure(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + VALID_DATETIME_LECTURE
                        + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE + RECURRING_DESC_LECTURE,
                expectedMessage);

        // missing duration prefix
        assertParseFailure(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                        + VALID_DURATION_LECTURE + MODULE_DESC_LECTURE + RECURRING_DESC_LECTURE,
                expectedMessage);

        // missing module prefix
        assertParseFailure(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                        + DURATION_DESC_LECTURE + VALID_MODULE_LECTURE + RECURRING_DESC_LECTURE,
                expectedMessage);

        // missing recurrence prefix
        assertParseFailure(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                        + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE + VALID_RECURRING_LECTURE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_LECTURE + VALID_URL_LECTURE + VALID_DATETIME_LECTURE
                        + VALID_DURATION_LECTURE + MODULE_DESC_LECTURE + VALID_RECURRING_LECTURE,
                expectedMessage);
    }

    //      TODO update once we do input validation
    //    @Test
    //    public void parse_invalidValue_failure() {
    //        // invalid name
    //        seedu.address.logic.parser.person.AddMeetingCommandParser oldParser = new seedu.address.logic.parser
    //        .person.AddMeetingCommandParser();
    //        assertParseFailure(oldParser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
    //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);
    //
    //        // invalid phone
    //        assertParseFailure(oldParser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
    //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);
    //
    //        // invalid email
    //        assertParseFailure(oldParser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
    //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);
    //
    //        // invalid address
    //        assertParseFailure(oldParser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
    //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);
    //
    //        // invalid tag
    //        assertParseFailure(oldParser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
    //                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);
    //
    //        // two invalid values, only first invalid value reported
    //        assertParseFailure(oldParser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
    //                Name.MESSAGE_CONSTRAINTS);
    //
    //        // non-empty preamble
    //        assertParseFailure(oldParser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
    //                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
    //                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
    //    }
}