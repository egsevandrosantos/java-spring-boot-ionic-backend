package com.evandrosantos.cursomc.services.exceptions;

public class MyUnirestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MyUnirestException(String msg) { super(msg); }

    public MyUnirestException(String msg, Throwable cause) { super(msg, cause); }
}
