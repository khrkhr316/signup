# Spring Boot Login Project

Spring Boot 기반 회원가입 / 로그인 웹 프로젝트
사용자 인증 기능과 데이터베이스 연동, 비밀번호 암호화, 이메일 인증 기능 등을 구현
Railway를 활용하여 실제 배포 진행

---

# Deploy URL

🔗 배포 주소
https://signup-production-828d.up.railway.app

🔗 GitHub Repository
https://github.com/khrkhr316/signup

---

# Tech Stack

## Backend

* Java
* Spring Boot
* Spring MVC
* Spring Security
* JDBC
* Thymeleaf

## Database

* MySQL
* Railway MySQL

## Frontend

* HTML
* CSS
* JavaScript

## Deploy

* Railway

---

# 주요 기능

## 회원가입

* 사용자 이름 / 이메일 / 비밀번호 입력
* BCrypt 비밀번호 암호화 저장
* 이메일 중복 체크 AJAX 구현
* 비밀번호 유효성 검사

  * 특수문자 포함
  * 영문 포함
  * 12자 이상

---

## 로그인

* 이메일 기반 로그인
* 세션 기반 사용자 인증
* 로그인 상태 유지

---

## 메인 페이지

* 로그인 사용자 정보 출력
* Session 기반 사용자 데이터 전달

---

## 비밀번호 찾기

* 임시 비밀번호 생성
* DB 비밀번호 자동 변경
* SMTP 이메일 전송 구현

※ Railway 무료 환경에서 Gmail SMTP timeout 이슈 발생으로 기능 사용 불가능.

---

# 프로젝트 구조

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

# 느낀 점

이번 프로젝트를 통해 단순 CRUD 구현을 넘어 실제 배포 환경에서 발생하는 다양한 문제를 경험할 수 있었습니다.

특히:

* Railway 클라우드 배포
* 환경변수 관리
* SMTP 네트워크 문제
* Git 충돌 해결
* 보안 이슈 대응

---

# Author

Blog
[티스토리](https://khr316.tistory.com/entry/signup)

---
