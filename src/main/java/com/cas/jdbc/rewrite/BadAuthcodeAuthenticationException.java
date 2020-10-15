package com.cas.jdbc.rewrite;


import org.jasig.cas.authentication.RootCasException;

public class BadAuthcodeAuthenticationException extends RootCasException {

    private static final long serialVersionUID = 5501212207531289993L;

    public static final String CODE = "error.authentication.authcode.bad";

    public BadAuthcodeAuthenticationException() {
        super(CODE);
    }

    public BadAuthcodeAuthenticationException(final Throwable throwable) {
        super(CODE, throwable);
    }
}