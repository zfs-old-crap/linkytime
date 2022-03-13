package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meetingentry.IsRecurring;
import seedu.address.model.meetingentry.MeetingDateTime;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.model.meetingentry.MeetingName;
import seedu.address.model.meetingentry.MeetingUrl;
import seedu.address.model.modulecode.ModuleCode;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link MeetingEntry}.
 */
class JsonAdaptedMeetingEntry {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting Entry's %s field is missing!";

    private final String name;
    private final String url;
    private final String dateTime;
    private final String moduleCode;
    private final String isRecurring;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedMeetingEntry} with the given meeting entry details.
     */
    @JsonCreator
    public JsonAdaptedMeetingEntry(@JsonProperty("name") String name,
                                   @JsonProperty("url") String url,
                                   @JsonProperty("dateTime") String dateTime,
                                   @JsonProperty("moduleCode") String moduleCode,
                                   @JsonProperty("isRecurring") String isRecurring,
                                   @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.url = url;
        this.dateTime = dateTime;
        this.moduleCode = moduleCode;
        this.isRecurring = isRecurring;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code MeetingEntry} into this class for Jackson use.
     */
    public JsonAdaptedMeetingEntry(MeetingEntry source) {
        name = source.getName().name;
        url = source.getUrl().meetingUrl.toExternalForm();
        dateTime = source.getDateTime().datetime;
        moduleCode = source.getModuleCode().moduleCode;
        isRecurring = source.getIsRecurring().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted meeting entry object into the model's {@code MeetingEntry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting entry.
     */
    public MeetingEntry toModelType() throws IllegalValueException {
        final List<Tag> meetingEntryTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            meetingEntryTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MeetingName.class.getSimpleName()));
        }
        if (!MeetingName.isValidName(name)) {
            throw new IllegalValueException(MeetingName.MESSAGE_CONSTRAINTS);
        }
        final MeetingName modelName = new MeetingName(name);

        if (url == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MeetingUrl.class.getSimpleName()));
        }
        if (!MeetingUrl.isValidUrl(url)) {
            throw new IllegalValueException(MeetingUrl.MESSAGE_CONSTRAINTS);
        }
        final MeetingUrl modelUrl = new MeetingUrl(url);

        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MeetingDateTime.class.getSimpleName()));
        }
        // TODO: To be uncommented when MeetingDateTime validation is implemented.
        // if (!dateTime.isValidDateTime(dateTime)) {
        //     throw new IllegalValueException(MeetingDateTime.MESSAGE_CONSTRAINTS);
        // }
        final MeetingDateTime modelDateTime = new MeetingDateTime(dateTime);

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        // TODO: To be uncommented when ModuleCode validation is implemented.
        // if (!moduleCode.isValidModuleCode(moduleCode)) {
        //     throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        // }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        if (isRecurring == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    IsRecurring.class.getSimpleName()));
        }
        if (!IsRecurring.isValidRecurringStatus(isRecurring)) {
            throw new IllegalValueException(IsRecurring.MESSAGE_CONSTRAINTS);
        }
        final IsRecurring modelIsRecurring = new IsRecurring(isRecurring);

        final Set<Tag> modelTags = new HashSet<>(meetingEntryTags);
        return new MeetingEntry(modelName, modelUrl, modelDateTime, modelModuleCode, modelIsRecurring, modelTags);
    }
}
