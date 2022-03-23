package seedu.address.model.meeting;

import java.io.IOException;

/**
 * Manages the opening of {@code URL} in device browser.
 */
public interface UrlOpener {
    /**
     * Opens the URL in device default browser
     *
     * @throws IOException thrown when default browser cannot be found or launched
     */
    void open() throws IOException;
}
