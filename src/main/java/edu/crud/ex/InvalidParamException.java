package edu.crud.ex;

public class InvalidParamException extends RuntimeException {
    public InvalidParamException(String allowedParams) {
        super("INVALID PARAMETER ENTERED. Only " + allowedParams + " are allowed");
    }
}
