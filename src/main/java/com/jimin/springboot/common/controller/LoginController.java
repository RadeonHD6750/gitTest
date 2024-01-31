package com.jimin.springboot.common.controller;

import com.jimin.springboot.common.mapper.ResponseVO;
import com.jimin.springboot.security.service.SecurityService;
import com.jimin.springboot.member.mapper.LoginCode;
import com.jimin.springboot.member.mapper.LoginVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@Controller
@RequestMapping("/session-login")
public class LoginController {

    private final SecurityService securityService;

    private final String trySession = "redirect:/session-login";
    private final String tryLogin = "redirect:/session-login/login";

    //차후 DB에서 가져와야함
    private final String[] loginResultMessageList = {"로그인 성공",
            "비밀번호가 틀렸습니다.",
            "둘다 틀렸거나 아예 가입도 안되어 있습니다."};

    private final String[] loginResultRedirectList = {"/session-login",
            "/session-login/login",
            "/session-login/login"};



    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping(value = {"", "/"})
    public String home(Model model,
                       HttpServletRequest request) {

        System.out.println("home session");
        HttpSession session = request.getSession(false);

        String redirectUrl = "home";

        if(session != null)
        {
            String userId = (String) session.getAttribute("USER_ID");
            String password = (String) session.getAttribute("PASSWORD");
            String userName = (String) session.getAttribute("USER_NAME");
            System.out.println(userId);
            System.out.println(password);
            System.out.println(userName);

            model.addAttribute("loginType", "session-login");
            model.addAttribute("pageName", "세션 로그인");

            LoginVO loginVO = new LoginVO();
            loginVO.setUserId(userId);
            loginVO.setPassword(password);
            loginVO.setUserName(userName);

            LoginVO loginCodeResult = securityService.login(loginVO);

            if(loginCodeResult.getLoginCode() == LoginCode.loginOK.getCode())
            {
                model.addAttribute("userId", loginCodeResult.getUserId());
                model.addAttribute("userName", loginCodeResult.getUserName());
                redirectUrl = "home";
            }
            else
            {
                redirectUrl = tryLogin;
            }
        }
        else
        {
            redirectUrl = tryLogin;
        }

        return redirectUrl;
    }


    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        LoginVO loginVO = new LoginVO();
        model.addAttribute("loginRequest", loginVO);
        return "login";
    }



    @ResponseBody
    @PostMapping("/login")
    public ResponseVO login(@RequestBody LoginVO loginVO,
                            BindingResult bindingResult,
                            HttpServletRequest request, Model model) throws Exception{

        System.out.println("login controller");
        System.out.println(loginVO.getUserId());
        System.out.println(loginVO.getPassword());
        System.out.println(loginVO.getUserName());

        ResponseVO resultMap = new ResponseVO();
        boolean responseResult = true;

        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        LoginVO loginCodeResult = securityService.login(loginVO);
        int loginCode = loginCodeResult.getLoginCode();

        if(loginCode == LoginCode.loginOK.getCode())
        {
            // 세션을 생성하기 전에 기존의 세션 파기
            request.getSession().invalidate();
            HttpSession session = request.getSession(true);  // Session이 없으면 생성

            // 세션에 userId를 넣어줌
            session.setAttribute("USER_ID", loginCodeResult.getUserId());
            session.setAttribute("PASSWORD", loginCodeResult.getPassword());
            session.setAttribute("USER_NAME", loginCodeResult.getUserName());
            session.setMaxInactiveInterval(1800); // Session이 30분동안 유지
        }
        securityService.updateLoginState(loginCodeResult);


        resultMap.setRedirectUrl(loginResultRedirectList[loginCode]);
        resultMap.setStatusCode(200);
        resultMap.setLoginCode(loginCode);
        resultMap.setMessage(loginResultMessageList[loginCode]);

        return resultMap;
    }


    @ResponseBody
    @PostMapping("/logout")
    public HashMap<String, Object> logout(HttpServletRequest request, Model model) {
        System.out.println("logout");

        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        HttpSession session = request.getSession(false);


        if(session != null)
        {
            String userId = (String) session.getAttribute("USER_ID");
            String password = (String) session.getAttribute("PASSWORD");
            String userName = (String) session.getAttribute("USER_NAME");

            LoginVO updateLogin = new LoginVO();
            updateLogin.setUserId(userId);
            updateLogin.setPassword(password);
            updateLogin.setUserName(userName);

            LoginCode updateCode = LoginCode.logout;
            updateLogin.setLoginCode(updateCode.getCode());

            securityService.updateLoginState(updateLogin);

            session.invalidate();
        }



        resultMap.put("redirectUrl", "/session-login/login");
        resultMap.put("message", "로그아웃되었습니다.");
        resultMap.put("responseResult", true);

        return resultMap;
    }

}
