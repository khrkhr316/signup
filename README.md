# Spring Boot Login Project

Spring Boot 기반 회원가입 / 로그인 웹 프로젝트 
사용자 인증, 데이터베이스 연동, 비밀번호 암호화, 이메일 인증 기능 구현 Railway를 활용해 실제 배포 진행

---

## 🚀 Deploy

- 🔗 Live Demo  
https://signup-production-828d.up.railway.app
└ 기간 만료

- 🔗 GitHub Repository  
https://github.com/khrkhr316/signup

---

## 🧰 Tech Stack

### Backend
- Java
- Spring Boot
- Spring MVC
- Spring Security
- JDBC
- Thymeleaf

### Database
- MySQL
- Railway MySQL

### Frontend
- HTML
- CSS
- JavaScript

### Deploy
- Railway

---

## ✨ 주요 기능

### 1. 회원가입
- 사용자 이름 / 이메일 / 비밀번호 입력
- BCrypt 비밀번호 암호화 저장
- 이메일 중복 체크 (AJAX)
- 비밀번호 유효성 검사
  - 특수문자 포함
  - 영문 포함
  - 12자 이상

---

### 2. 로그인
- 이메일 기반 로그인
- 세션(Session) 기반 인증
- 로그인 상태 유지

---

### 3. 메인 페이지
- 로그인 사용자 정보 출력
- Session 기반 사용자 데이터 전달

---

### 4. 비밀번호 찾기
- 임시 비밀번호 생성
- DB 비밀번호 자동 변경
- SMTP 이메일 전송 기능 구현

⚠️ Railway 무료 환경에서 Gmail SMTP Timeout 문제로 실제 메일 발송은 제한됨

---

## 📁 프로젝트 구조

```bash
src
 ┣ controller
 ┣ dao
 ┣ security
 ┣ templates
 ┣ static
 ┗ application.properties
```

---

# 느낀 점 및 개선하면 좋을 점

- 로컬과 배포 환경은 완전히 다름
- 환경변수 관리의 중요성
- 클라우드 DB 연결 구조 이해
- SMTP 네트워크 제약 경험
- 실제 서비스 배포 과정 이해

- OAuth 로그인 (Google / Kakao)
- 이메일 전송 API 변경 (SendGrid / Resend)
- Spring Security 구조 개선
- 프론트 UI 개선

---

# Author

[티스토리](https://khr316.tistory.com/entry/signup)

---
