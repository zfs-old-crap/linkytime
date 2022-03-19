package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.LinkyTime;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.model.modulecode.ModuleCode;

/**
 * An immutable LinkyTime that is serializable to JSON format.
 */
@JsonRootName(value = "linkytime")
class JsonSerializableLinkyTime {
    public static final String MESSAGE_DUPLICATE_MEETING_ENTRY =
            "Meeting entries list contains duplicate meeting entry(s).";
    public static final String MESSAGE_DUPLICATE_MODULE_CODE =
            "Module codes list contains duplicate module code(s).";

    private final List<JsonAdaptedMeetingEntry> meetingEntries = new ArrayList<>();
    private final List<JsonAdaptedModuleCode> moduleCodes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableLinkyTime} with the given meeting entries.
     */
    @JsonCreator
    public JsonSerializableLinkyTime(@JsonProperty("meetingEntries") List<JsonAdaptedMeetingEntry> meetingEntries,
                                     @JsonProperty("moduleCodes") List<JsonAdaptedModuleCode> moduleCodes) {
        this.meetingEntries.addAll(meetingEntries);
        this.moduleCodes.addAll(moduleCodes);
    }

    /**
     * Converts a given {@code ReadOnlyLinkyTime} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLinkyTime}.
     */
    public JsonSerializableLinkyTime(ReadOnlyLinkyTime source) {
        meetingEntries.addAll(
                source.getMeetingEntryList().stream().map(JsonAdaptedMeetingEntry::new).collect(Collectors.toList()));
        moduleCodes.addAll(
                source.getModuleCodeList().stream().map(JsonAdaptedModuleCode::new).collect(Collectors.toList()));
    }

    /**
     * Converts this LinkyTime into the model's {@code LinkyTime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public LinkyTime toModelType() throws IllegalValueException {
        final LinkyTime linkyTime = new LinkyTime();

        for (final JsonAdaptedMeetingEntry jsonAdaptedMeetingEntry : meetingEntries) {
            final MeetingEntry meetingEntry = jsonAdaptedMeetingEntry.toModelType();
            if (linkyTime.hasMeetingEntry(meetingEntry)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MEETING_ENTRY);
            }
            linkyTime.addMeetingEntry(meetingEntry);
        }

        for (final JsonAdaptedModuleCode jsonAdaptedModuleCode : moduleCodes) {
            final ModuleCode moduleCode = jsonAdaptedModuleCode.toModelType();
            if (linkyTime.hasModuleCode(moduleCode)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE_CODE);
            }
            linkyTime.addModuleCode(moduleCode);
        }

        return linkyTime;
    }

}
