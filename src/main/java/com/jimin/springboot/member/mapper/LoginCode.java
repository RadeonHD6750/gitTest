package com.jimin.springboot.member.mapper;

public enum LoginCode {
    loginOK(0),
    wrongPassword(1),
    notFound(2),
    logout(3);

    private final int code;

    LoginCode(int code)
    {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
