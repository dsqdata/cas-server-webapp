package com.cas.jdbc.rewrite;

import org.jasig.cas.authentication.RootCasException;

public class NullAuthcodeAuthenticationException extends RootCasException {

    private static final long serialVersionUID = 5501212207531289993L;

    public static final String CODE = "required.authcode";

    public NullAuthcodeAuthenticationException() {
        super(CODE);
    }

    public NullAuthcodeAuthenticationException(final Throwable throwable) {
        super(CODE, throwable);
    }
}