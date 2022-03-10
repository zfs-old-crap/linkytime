package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.LinkyTime;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.meetingentry.IsRecurring;
import seedu.address.model.meetingentry.MeetingDateTime;
import seedu.address.model.meetingentry.MeetingEntry;
import seedu.address.model.meetingentry.MeetingName;
import seedu.address.model.meetingentry.MeetingUrl;
import seedu.address.model.tag.Tag;

/**
 * A class to access LinkyTime data stored as a json file on the hard disk.
 */
public class JsonLinkyTimeStorage implements LinkyTimeStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonLinkyTimeStorage.class);

    // Temporary dummy data to substitute data file from storage while this class is still being implemented.
    private static final LinkyTime DUMMY_DATA = new LinkyTime();
    static {
        final MeetingUrl dummyMeetingUrl = new MeetingUrl("https://legit-uni.zoom.us/j/344299221?pwd=F3a99221");
        JsonLinkyTimeStorage.DUMMY_DATA.setMeetingEntries(Arrays.asList(
                new MeetingEntry(
                        new MeetingName("CS2103T Lecture"),
                        dummyMeetingUrl,
                        new MeetingDateTime("18mar2022"),
                        new IsRecurring("Y"),
                        new HashSet<Tag>()
                ),
                new MeetingEntry(
                        new MeetingName("CS2101 Tutorial"),
                        dummyMeetingUrl,
                        new MeetingDateTime("19mar2022"),
                        new IsRecurring("Y"),
                        new HashSet<Tag>()
                ),
                new MeetingEntry(
                        new MeetingName("TokTik Rejection Interview"),
                        dummyMeetingUrl,
                        new MeetingDateTime("20mar2022"),
                        new IsRecurring("N"),
                        new HashSet<Tag>()
                )
        ));
    }

    private final Path filePath;

    public JsonLinkyTimeStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getLinkyTimeFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyLinkyTime> readLinkyTime() throws DataConversionException {
        return readLinkyTime(filePath);
    }

    /**
     * Similar to {@link #readLinkyTime()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyLinkyTime> readLinkyTime(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        // TODO: Retrieval from data file.
        return Optional.<ReadOnlyLinkyTime>of(JsonLinkyTimeStorage.DUMMY_DATA);
    }

    @Override
    public void saveLinkyTime(ReadOnlyLinkyTime linkyTime) throws IOException {
        saveLinkyTime(linkyTime, filePath);
    }

    /**
     * Similar to {@link #saveLinkyTime(ReadOnlyLinkyTime)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveLinkyTime(ReadOnlyLinkyTime linkyTime, Path filePath) throws IOException {
        requireNonNull(linkyTime);
        requireNonNull(filePath);

        // TODO: Persistence to data file.
    }

}
