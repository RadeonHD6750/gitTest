package com.jimin.springboot.member.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    MemberVO idCheck(String user_id);

    MemberVO loginMember(LoginVO loginVO);

    void updateLoginState(LoginVO loginVO);

    void insertMember(MemberVO memberVO);

    void updateMember(MemberVO memberVO);

    void enableMember(MemberVO memberVO);

    void disableMember(MemberVO memberVO);

    void deleteMember(MemberVO memberVO);
}
