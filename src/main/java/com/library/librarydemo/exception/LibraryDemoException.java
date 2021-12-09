package com.library.librarydemo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class LibraryDemoException extends RuntimeException {

    private static final long serialVersionUID = -3240433471054008L;
    public static final String UNKNOWN_ERROR_ENCOUNTERED = "Unknown error encountered";

    @Getter
    private final HttpStatus httpStatus;

    public LibraryDemoException(Throwable e, HttpStatus statusCode) {
        super(UNKNOWN_ERROR_ENCOUNTERED, e);
        httpStatus = statusCode;
    }

    public LibraryDemoException(String message) {
        this(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public LibraryDemoException(Throwable e) {
        this(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
