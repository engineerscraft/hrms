package com.hamdard.hua.exception;

public class ResourceNotFound extends RuntimeException {

    static final long serialVersionUID = 3L;
    
    public ResourceNotFound(String message) {
        super(message);
    }
}
