package com.jimin.springboot.member.mapper;

import lombok.Getter;
import lombok.Setter;
import oracle.sql.DATE;

import java.sql.Date;

@Getter
@Setter
public class MemberVO {
    private int userNumber;
    private String userId;
    private String password;
    private String userName;
    private String email;
    private Date createDate;
    private Date updateDate;
}
