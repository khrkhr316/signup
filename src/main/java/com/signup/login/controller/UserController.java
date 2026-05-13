package com.signup.login.controller;

import org.springframework.stereotype.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.signup.login.dao.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    // 비밀번호 암호화 추가 (SecurityConfig 연결)
    @Autowired
    PasswordEncoder passwordEncoder;

    // 회원가입 페이지 주소 설정
    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    // UserDao 연결
    @Autowired
    UserDao userDao;

    // signup.html form에서 보낸 값 받기
    @PostMapping("/signup")
    public String signup(@RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        // 비밀번호 암호화 하기
        String encodedPassword = passwordEncoder.encode(password);

        // DB에 암호화 된 비밀번호 값 보내기
        userDao.insertSignup(username, email, encodedPassword);
        // userDao.insertSignup로 값 전송

        System.out.println(
                "회원가입 시작========================================");
        System.out.println(encodedPassword);

        System.out.println(email + "/" + username + "/" + password);

        return "redirect:/login";
    }

    // 로그인 페이지 주소 설정
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    // login.html form에서 보낸 값 받기
    @PostMapping("/login")
    public String login(@RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session) {
        // 이메일 일치하는 데이터 있는 지 확인
        // userDao.selectCntUser()
        Map<String, Object> user = userDao.findByEmail(email);

        // DB에 이메일 존재하고, 비밀번호 매치 비교
        // session에 이메일 저장 후 메인 화면으로 이동
        if (user != null &&
                passwordEncoder.matches(password, (String) user.get("password"))) {

            session.setAttribute("email", email);

            return "redirect:/";
        }
        // 없을 경우 에러 메세지와 함께 로그인 페이지 유지
        else {
            model.addAttribute("error", "이메일 또는 비밀번호를 확인하세요");
            return "user/login";
        }
    }

    // 메인 페이지 주소 설정
    @GetMapping("/")
    public String main(HttpSession session, Model model) {
        // 메인 페이지에 session에 저장 된 이메일 전송
        Object loginUser = session.getAttribute("email");
        model.addAttribute("loginUser", loginUser);

        return "main";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // session 종료
        session.invalidate();
        return "redirect:/";
    }

    // 이메일 중복 체크
    @PostMapping("/check-email")
    @ResponseBody // javascript 값 넘겨주기
    public boolean checkEmail(@RequestParam String email) {

        System.out.println(email + "===============================");
        // DB에 email 존재 유무 넘겨주기
        return userDao.isEmailExists(email);
    }

}