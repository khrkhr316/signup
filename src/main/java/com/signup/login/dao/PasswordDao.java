package com.signup.login.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordDao {
    // jdbc 연결
    @Autowired
    JdbcTemplate jt;

    // 이름,이메일 일치하는 회원 유무 조회
    public boolean checkUserExists(String id, String email) {
        String sql = "select count(*) from users ";
        sql += "where username = ? and ";
        sql += "email = ?";

        Integer count = jt.queryForObject(sql,Integer.class, new Object[] { id, email });

        return count != null && count > 0;
    }

    // 발급된 임시비밀번호로 업데이트
    public void updatePassword(String email, String encondeNewPassword) {
        String sqlStmt = "UPDATE users SET password = ? WHERE email = ?";
        jt.update(sqlStmt, encondeNewPassword, email);
    }

}
