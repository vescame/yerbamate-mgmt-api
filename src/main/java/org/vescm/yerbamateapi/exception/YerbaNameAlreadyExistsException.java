package org.vescm.yerbamateapi.exception;

public class YerbaNameAlreadyExistsException extends Exception {
    public YerbaNameAlreadyExistsException(final String yerbaName) {
        super(String.format("Yerba Mate with name %s already exists!", yerbaName));
    }
}
