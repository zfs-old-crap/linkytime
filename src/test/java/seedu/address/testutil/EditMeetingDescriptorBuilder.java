package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.meetingentry.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.meetingentry.IsRecurring;
import seedu.address.model.meetingentry.MeetingDateTime;
import seedu.address.model.meetingentry.MeetingDuration;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.model.meetingentry.MeetingName;
import seedu.address.model.meetingentry.MeetingUrl;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditMeetingDescriptor objects.
 */
public class EditMeetingDescriptorBuilder {
    private EditMeetingDescriptor descriptor;

    public EditMeetingDescriptorBuilder() {
        descriptor = new EditMeetingDescriptor();
    }

    public EditMeetingDescriptorBuilder(EditMeetingDescriptor descriptor) {
        this.descriptor = new EditMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMeetingDescriptor} with fields containing {@code meeting}'s details
     */
    public EditMeetingDescriptorBuilder(MeetingEntry meeting) {
        descriptor = new EditMeetingDescriptor();
        descriptor.setName(meeting.getName());
        descriptor.setUrl(meeting.getUrl());
        descriptor.setDateTime(meeting.getDateTime());
        descriptor.setDuration(meeting.getDuration());
        descriptor.setModule(meeting.getModule());
        descriptor.setIsRecurring(meeting.getIsRecurring());
        descriptor.setTags(meeting.getTags());
    }

    /**
     * Sets the {@code MeetingName} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withName(String name) {
        descriptor.setName(new MeetingName(name));
        return this;
    }

    /**
     * Sets the {@code Url} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withUrl(String url) {
        descriptor.setUrl(new MeetingUrl(url));
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDateTime(String dateTime) {
        descriptor.setDateTime(new MeetingDateTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDuration(String duration) {
        descriptor.setDuration(new MeetingDuration(duration));
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withModule(String module) {
        descriptor.setModule(new Module(module));
        return this;
    }

    /**
     * Sets the {@code IsRecurring} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withIsRecurring(String isRecurring) {
        descriptor.setIsRecurring(new IsRecurring(isRecurring));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditMeetingDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditMeetingDescriptor build() {
        return descriptor;
    }
}
