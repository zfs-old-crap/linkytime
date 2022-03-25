package seedu.address.model.meeting;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import seedu.address.model.meeting.exceptions.UnsupportedDesktopException;

public class UrlOpenerManager implements UrlOpener {
    private static final String MESSAGES_INVALID_URL = "Invalid URL provided";
    private Desktop desktop;
    private URI uri;

    /**
     * Creates a {@code UrlOpenerManager} and checks if the device supports
     * the {@code java.awt.Desktop} class and the {@code java.awt.Desktop::browse} method.
     *
     * @throws UnsupportedDesktopException thrown when the {@code Desktop} class or
     *                                     {@code Desktop::browse} method is unsupported
     */
    public UrlOpenerManager() throws UnsupportedDesktopException {
        if (!checkAndSetDesktop()) {
            throw new UnsupportedDesktopException();
        }
    }

    @Override
    public void open(URL url) throws SecurityException, IOException {
        if (!checkAndSetUrl(url)) {
            throw new IllegalArgumentException(MESSAGES_INVALID_URL);
        }
        desktop.browse(uri);
    }

    private boolean checkAndSetDesktop() {
        if (!Desktop.isDesktopSupported()) {
            return false;
        }
        desktop = Desktop.getDesktop();
        return desktop.isSupported(Desktop.Action.BROWSE);
    }

    private boolean checkAndSetUrl(URL url) {
        try {
            uri = url.toURI();
            return true;
        } catch (URISyntaxException ex) {
            return false;
        }
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













