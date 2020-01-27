package com.mafia.exceptions;

/**
 * created by girija-4135 on 26/01/20
 */
public class DBException extends Exception {

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
    public DBException(Throwable cause) {
        super(cause);
    }
}
