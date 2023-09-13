package se.lexicon.exceptions.workshop.exception;

import java.io.IOException;

public class DuplicateNameException extends IOException {

    public DuplicateNameException(String message) {
        super(message);
    }
}
