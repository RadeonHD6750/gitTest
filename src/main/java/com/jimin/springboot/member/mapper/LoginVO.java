package com.jimin.springboot.member.mapper;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginVO {
    private String userId;
    private String password;
    private String userName;
    private int loginCode; // 0 - 로그인, 1 - 비밀번호가 틀림, 2 - 없거나 둘다 잘못씀
}
