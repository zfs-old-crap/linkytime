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
import seedu.address.model.module.Module;

/**
 * An immutable LinkyTime that is serializable to JSON format.
 */
@JsonRootName(value = "linkytime")
class JsonSerializableLinkyTime {
    public static final String MESSAGE_DUPLICATE_MEETING_ENTRY =
            "Meeting entries list contains duplicate meeting entry(s).";
    public static final String MESSAGE_DUPLICATE_MODULE =
            "Modules list contains duplicate module(s).";

    private final List<JsonAdaptedMeetingEntry> meetingEntries = new ArrayList<>();
    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableLinkyTime} with the given meeting entries.
     */
    @JsonCreator
    public JsonSerializableLinkyTime(@JsonProperty("meetingEntries") List<JsonAdaptedMeetingEntry> meetingEntries,
                                     @JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.meetingEntries.addAll(meetingEntries);
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyLinkyTime} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLinkyTime}.
     */
    public JsonSerializableLinkyTime(ReadOnlyLinkyTime source) {
        meetingEntries.addAll(
                source.getMeetingEntryList().stream().map(JsonAdaptedMeetingEntry::new).collect(Collectors.toList()));
        modules.addAll(
                source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
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

        for (final JsonAdaptedModule jsonAdaptedModule : modules) {
            final Module module = jsonAdaptedModule.toModelType();
            if (linkyTime.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            linkyTime.addModule(module);
        }

        return linkyTime;
    }

}
