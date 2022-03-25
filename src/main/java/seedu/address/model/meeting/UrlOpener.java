package seedu.address.model.meeting;

import java.io.IOException;
import java.net.URL;

/**
 * Manages the opening of {@code URL} in device browser.
 */
public interface UrlOpener {
    /**
     * Opens the URL in device default browser
     *
     * @throws IOException thrown when default browser cannot be found or launched
     */
    void open(URL url) throws IOException;
}
