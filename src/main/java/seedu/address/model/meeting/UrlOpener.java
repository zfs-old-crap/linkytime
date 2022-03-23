package seedu.address.model.meeting;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Manages the conversion of {@code URL} to intermediate forms and opening it in device browser.
 */
public interface UrlOpener {
    /**
     * Converts the given {@code URL} to a {@code URI}
     * and sets the {@code uri} field of the {@code UrlOpener}.
     *
     * @throws URISyntaxException thrown when {@code URL} is not a valid {@code URI}
     */
    void setUri(URL url) throws URISyntaxException;

    /**
     * Opens the URL in device default browser
     *
     * @throws IOException thrown when default browser cannot be found or launched
     */
    void open() throws IOException;
}
