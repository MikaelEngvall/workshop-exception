package se.lexicon.exceptions.workshop.exception;

import java.io.IOException;

public class FileWriteException extends IOException{

    public FileWriteException(String message) {
        super(message);
    }
}
