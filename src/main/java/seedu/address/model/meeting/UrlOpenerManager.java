package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import seedu.address.model.meeting.exceptions.UnsupportedDesktopException;

public class UrlOpenerManager implements UrlOpener {
    private final Desktop desktop;
    private final URI uri;

    /**
     * Creates a {@code UrlOpenerManager} and checks if the device supports
     * the {@code java.awt.Desktop} class and the {@code java.awt.Desktop::browse} method.
     *
     * @throws UnsupportedDesktopException thrown when the {@code Desktop} class or
     *                                     {@code Desktop::browse} method is unsupported
     */
    public UrlOpenerManager(URL url) throws UnsupportedDesktopException, URISyntaxException {
        requireNonNull(url);
        uri = url.toURI();

        if (!Desktop.isDesktopSupported()) {
            throw new UnsupportedDesktopException();
        }
        desktop = Desktop.getDesktop();
        if (!desktop.isSupported(Desktop.Action.BROWSE)) {
            throw new UnsupportedDesktopException();
        }
    }

    @Override
    public void open() throws SecurityException, IOException {
        desktop.browse(uri);
    }

    @Override
    public String toString() {
        return uri.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UrlOpenerManager // instanceof handles nulls
                && uri.equals(((UrlOpenerManager) other).uri)); // state check
    }

    @Override
    public int hashCode() {
        return uri.hashCode();
    }
}













