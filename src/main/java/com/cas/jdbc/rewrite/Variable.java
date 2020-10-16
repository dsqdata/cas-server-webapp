package com.cas.jdbc.rewrite;

public class Variable {

    public static String expire;

    public static  String pathUrl;
    public static  String pathUrlN;
    public static  String loginUrl ;
    public static String username;
    public static String password ;
    public static  String templateN;

    public void setExpire(String expire) {
        this.expire = expire;

    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public void setPathUrlN(String pathUrlN) {
        this.pathUrlN = pathUrlN;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTemplateN(String templateN) {
        this.templateN = templateN;
    }
}
