/*
* Media Message SubClass
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp.Interactions;

import TypeValues.MediaType;
import java.io.File;

public class MediaMessage extends Message {

    // Attributes
    private String pathToFile;
    private MediaType fileType;

    // Constructor
    public MediaMessage(Contact sender, String pathToFile)
            throws InvalidPathException, InvalidFileFormatException {
        super(sender);
        File fileCheck = new File(pathToFile);
        if (!fileCheck.exists()) {
            throw new InvalidPathException();
        }
        this.pathToFile = pathToFile;
        this.fileType = setFileType(pathToFile);
    }

    // Getters
    public String getPathToFile() {
        return pathToFile;
    }

    public MediaType getFileType() {
        return fileType;
    }

    // Method
    private static MediaType setFileType(String path)
            throws InvalidFileFormatException {
        if (path.toLowerCase().contains(".jpg")
                || path.toLowerCase().contains(".png")
                || path.toLowerCase().contains(".gif")) {
            return MediaType.PHOTO;
        } else if (path.toLowerCase().contains(".mp4")
                || path.toLowerCase().contains(".avi")) {
            return MediaType.VIDEO;
        } else if (path.toLowerCase().contains(".pdf")
                || path.toLowerCase().contains(".doc")) {
            return MediaType.DOCUMENT;
        } else if (path.toLowerCase().contains(".mp3")) {
            return MediaType.AUDIO;
        } else {
            int check = path.indexOf(".");
            if (check != -1) {
                throw new InvalidFileFormatException(path.substring(check));
            } else {
                throw new InvalidFileFormatException();
            }
        }

    }

    // To String
    @Override
    public String toString() {
        return sender.getProfile().getName() + ": " + getFileType() + " in path " + getPathToFile() + ".";
    }
}
