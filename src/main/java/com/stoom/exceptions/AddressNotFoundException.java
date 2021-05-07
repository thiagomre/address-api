package com.stoom.exceptions;

public class AddressNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Entity not found exception.
     *
     * @param msg the msg
     */
    public AddressNotFoundException(String msg) {
        super(msg);
    }

    private AddressNotFoundException() {
    }
}
