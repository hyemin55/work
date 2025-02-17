package com.example.ex01.member;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MemberRepository {

    public void select(){
        List<Member> list = new ArrayList<>();
        try( Connection conn
                     = DriverManager.getConnection(
                "jdbc:mysql://192.168.0.29:3307/pmh","root","1234") ){
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM member ORDER BY IDX DESC");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Member member = Member.builder()
                        .name(rs.getString("name"))
                        .idx(rs.getInt("idx"))
                        .age(rs.getInt("age"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .regdate(rs.getObject("regdate", LocalDateTime.class))
                        .mydate(rs.getObject("mydate", LocalDateTime.class))
                        .build();
                list.add(member);
            }
            list.stream()
                    .forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void insert(String name, Integer age){
        LocalDateTime mydate = LocalDateTime.now();

        try( Connection conn
                     = DriverManager.getConnection(
                "jdbc:mysql://localhost/pmh","root","12341234") ){
            PreparedStatement pstmt
                    = conn.prepareStatement("""
                        INSERT INTO aa 
                            (NAME,age,email,password,mydate)
                        values
                            (?,?,?,?,?)
                    """);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3,"aaaa@naver.com");
            pstmt.setString(4,"password");
            pstmt.setObject(5, mydate);

            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    // 사용자존재유무 확인
    public Member findByIdx(int idx){
        try(Connection conn =
                    DriverManager.getConnection(
                            "jdbc:mysql://192.168.0.29:3307/pmh",
                            "root",
                            "1234")
        ){
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * from member where idx = ? ");
            pstmt.setInt(1, idx);
            ResultSet rs = pstmt.executeQuery();
            if( rs.next()){
                return Member.builder()
                        .idx(rs.getInt("idx"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .build();
            }
            else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}













