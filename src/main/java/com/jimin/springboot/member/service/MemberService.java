package com.jimin.springboot.member.service;

import com.jimin.springboot.member.mapper.LoginVO;
import com.jimin.springboot.member.mapper.MemberMapper;
import com.jimin.springboot.member.mapper.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    public MemberVO getLoginMember(LoginVO requestLoginVO)
    {
        return memberMapper.loginMember(requestLoginVO);
    }
}
