package com.cas.jdbc.rewrite;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jasig.cas.authentication.UsernamePasswordCredential;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsernamePasswordCaptchaCredential extends
        UsernamePasswordCredential {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, message = "required.authcode")
    private String authcode;

    public final String getAuthcode() {
        return authcode;
    }

    public final void setAuthcode(String authcode) {
        this.authcode = authcode;
    }


    @NotNull
    @Size(min = 1, message = "required.authtype")
    private String authtype;

    public String getAuthtype() {
        return authtype;
    }

    public void setAuthtype(String authtype) {
        this.authtype = authtype;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final UsernamePasswordCaptchaCredential that = (UsernamePasswordCaptchaCredential) o;

        if (getPassword() != null ? !getPassword().equals(that.getPassword())
                : that.getPassword() != null) {
            return false;
        }

        if (getPassword() != null ? !getPassword().equals(that.getPassword())
                : that.getPassword() != null) {
            return false;
        }
        if (authcode != null ? !authcode.equals(that.authcode)
                : that.authcode != null)
            return false;
        if (authtype != null ? !authtype.equals(that.authtype)
                : that.authtype != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getUsername())
                .append(getPassword()).append(authcode).append(authtype).toHashCode();
    }

}