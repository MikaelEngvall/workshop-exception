package se.lexicon.exceptions.workshop.exception;

public class FileWriteException extends Exception{

    public FileWriteException(String message) {
        super(message);
    }

    public String friendlyErrorMessage() {
        return "Something went wrong :D, and this is what happened: " + getMessage();
    }
}
