package org.devbuild.algoservice.exception;

public enum ExceptionConstants {

    GENERIC_EXCEPTION("general.exception");

    private String message;

    private ExceptionConstants(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
