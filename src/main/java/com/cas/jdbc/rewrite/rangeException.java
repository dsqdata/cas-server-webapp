package com.cas.jdbc.rewrite;

import org.jasig.cas.authentication.RootCasException;

public class rangeException extends RootCasException {

    private static final long serialVersionUID = 5501212207531289993L;

    public static final String CODE = "error.authentication.authcode.range";

    public rangeException() {
        super(CODE);
    }

    public rangeException(final Throwable throwable) {
        super(CODE, throwable);
    }
}