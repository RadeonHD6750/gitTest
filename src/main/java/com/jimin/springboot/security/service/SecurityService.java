package com.jimin.springboot.security.service;

import com.jimin.springboot.member.mapper.LoginCode;
import com.jimin.springboot.member.mapper.LoginVO;
import com.jimin.springboot.member.mapper.MemberMapper;
import com.jimin.springboot.member.mapper.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SecurityService {

    MemberMapper memberMapper;

    private final String tryLogin = "redirect:/session-login";

    @Autowired
    public SecurityService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    public LoginVO login(LoginVO requestLoginVO)
    {
        LoginCode loginCodeResult = LoginCode.notFound;

        LoginVO loginResult = new LoginVO();

        MemberVO memberDB = memberMapper.loginMember(requestLoginVO);


        if(memberDB != null)
        {
            System.out.println(memberDB.getUserId());
            System.out.println(memberDB.getPassword());


            String dbUserId = memberDB.getUserId();
            String dbPassword = memberDB.getPassword();
            String dbUserName = memberDB.getUserName();
            String requestUserId = requestLoginVO.getUserId();
            String requestPassword = requestLoginVO.getPassword();

            if(dbUserId.equals(requestUserId) && dbPassword.equals(requestPassword))
            {
                loginCodeResult = LoginCode.loginOK;
                loginResult.setUserId(dbUserId);
                loginResult.setPassword(dbPassword);
                loginResult.setUserName(dbUserName);
            }
            else if(dbUserId.equals(requestUserId))
            {
                loginCodeResult = LoginCode.wrongPassword;
            }
            else
            {
                loginCodeResult = LoginCode.notFound;
            }
        }
        else
        {
            loginCodeResult = LoginCode.notFound;
        }

        loginResult.setLoginCode(loginCodeResult.getCode());

        return loginResult;
    }

    public void updateLoginState(LoginVO requestLoginVO)
    {
        memberMapper.updateLoginState(requestLoginVO);
    }


    public String forwardLoginProtect(HttpServletRequest request, String forwardUrl)
    {

        String redirectUrl = forwardUrl;
        if(!isLoginSession(request))
        {
            System.out.println("로그인 안되어 있음");
            redirectUrl = tryLogin;
        }
        return redirectUrl;
    }


    public boolean isLoginSession(HttpServletRequest request)
    {
        boolean isLoginOK = false;
        HttpSession session = request.getSession(false);

        if(session != null)
        {
            String userId = (String) session.getAttribute("USER_ID");
            String password = (String) session.getAttribute("PASSWORD");

            LoginVO requestLoginVO = new LoginVO();
            requestLoginVO.setUserId(userId);
            requestLoginVO.setPassword(password);

            requestLoginVO = login(requestLoginVO);

            if(requestLoginVO.getLoginCode() == LoginCode.loginOK.getCode())
            {
                isLoginOK = true;
            }
            else
            {
                isLoginOK = false;
            }
        }
        else
        {
            isLoginOK = false;
        }

        return isLoginOK;
    }
}
