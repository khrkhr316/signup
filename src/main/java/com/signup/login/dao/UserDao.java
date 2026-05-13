package com.signup.login.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    // jdbc 연결
    @Autowired
    JdbcTemplate jt;

    // controller signup 에서 보내준 input 값 DB에 저장하기
    public void insertSignup(String username,
            String email,
            String encodedpassword) {
        String sql = "insert into users(username,email,password) ";
        sql += "values(?,?,?)";

        jt.update(sql, username, email, encodedpassword);
    }

    // controller login 에서 보내준 input 값 조회하기
    public Map<String, Object> findByEmail(String email) {
        String sql = "select * from users where email = ?";

        return jt.queryForMap(sql, email);
    }

    // 이메일 중복 체크
    // public boolean isEmailExists(String email){
    // String sql = "select count(*) from `user` ";
    // sql += "where email = ?";

    // int tf = jt.queryForObject(sql, int.class, email);

    // // DB에 이미 존재하면 True, 존재안하면(값=0) False 반환
    // return tf > 0;
    // }

    public boolean isEmailExists(String email) {

        try {
            System.out.println("EMAIL CHECK: " + email);

            String sql = "select count(*) from users where email = ?";

            Integer result = jt.queryForObject(sql, Integer.class, email);

            System.out.println("DB RESULT: " + result);

            return result != null && result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
