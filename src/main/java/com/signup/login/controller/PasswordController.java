package com.signup.login.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.signup.login.dao.PasswordDao;

@Controller
public class PasswordController {
    // PasswordDao 연결
    @Autowired
    private PasswordDao passwordDao;

    // JavaMailSender 연결
    @Autowired
    private JavaMailSender javaMailSender;

    // 비밀번호 찾기 페이지 주소 설정
    @GetMapping("/password")
    public String findPasswordPage(){
        return "password";
    }

    // 비밀번호 찾기 (가입한 이름과 이메일 사용)
    @GetMapping("/check/findPw")
    // javascript로 값 보내기
    @ResponseBody
    // html에서 username,email 받아오기
    public Map<String, Boolean> checkUser(@RequestParam String id,
                                        @RequestParam String email){
        // username,email 일치하는 회원 유무 True, False 
        boolean exists = passwordDao.checkUserExists(id,email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("check", exists);
        return response;
    }

    // 임시 비밀번호 암호화 (SecurityConfig 연결)
    @Autowired
    PasswordEncoder passwordEncoder;

    // 입력한 이름과 이메일이 DB에 있는 경우 임시비밀번호 이메일로 발급하기
    @PostMapping("/check/findPw/sendEmail")
    @ResponseBody // javascript로 값 보내기
    public void sendTempPassword(@RequestParam String id, 
        @RequestParam String email){
        
        // 임시 비밀번호로 데이터 수정 (암호화 필요)
        String newPassword = generateRandomPassword();
        String encondeNewPassword = passwordEncoder.encode(newPassword);
        passwordDao.updatePassword(email, encondeNewPassword);

        // 이메일로 보낼 문구 설정
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("${MAIL_USERNAME}");
        message.setTo(email);
        message.setSubject("KHR 임시 비밀번호 발급");
        message.setText(id+"님의 임시비밀번호는 "+ newPassword + " 입니다.");

        System.out.println(email+"=================================");

        // 이메일 보내기
        try {
            javaMailSender.send(message);
            System.out.println("메일 발송 성공");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("메일 발송 실패: "+ e.getMessage());
        }
    
    }

    // 임시 비밀번호 랜덤 설정
    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    
}
