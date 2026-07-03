package util;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Provides generic file system and serialization helper methods for persistence.
 */
public final class FileUtil {

    private FileUtil() {
    }

    /**
     * Checks whether a file or directory exists at the specified path.
     *
     * @param path the path to inspect
     * @return true if the path exists
     */
    public static boolean fileExists(Path path) {
        return path != null && Files.exists(path);
    }

    /**
     * Creates a directory if it does not already exist.
     *
     * @param path the directory path to create
     * @return true if the directory exists after the operation
     */
    public static boolean createDirectoryIfMissing(Path path) {
        if (path == null) {
            return false;
        }
        try {
            Files.createDirectories(path);
            return Files.isDirectory(path);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Creates a file if it does not already exist.
     *
     * @param path the file path to create
     * @return true if the file exists after the operation
     */
    public static boolean createFileIfMissing(Path path) {
        if (path == null) {
            return false;
        }
        try {
            if (Files.exists(path)) {
                return Files.isRegularFile(path);
            }
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            Files.createFile(path);
            return Files.isRegularFile(path);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Saves a serializable object to the specified path.
     *
     * @param path the target file path
     * @param object the object to serialize
     * @return true if the save succeeded
     */
    public static boolean saveObject(Path path, Object object) {
        if (path == null || object == null) {
            return false;
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(object);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Loads an object from the specified path.
     *
     * @param path the source file path
     * @param <T> the expected type of the object
     * @return the loaded object, or null if loading fails
     */
    @SuppressWarnings("unchecked")
    public static <T> T loadObject(Path path) {
        if (path == null || !Files.exists(path)) {
            return null;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path))) {
            return (T) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Closes a closeable resource safely.
     *
     * @param resource the resource to close
     */
    public static void safeClose(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Ignore close failures.
            }
        }
    }
}
