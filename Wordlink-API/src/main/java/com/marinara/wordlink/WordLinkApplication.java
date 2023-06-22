package com.marinara.wordlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class WordLinkApplication {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
//        String user = "root";
//        String password = "Password123";
//        String url = "jdbc:mysql://localhost:3306/wordlink?autoReconnect=true&useSSL=false";
//
//        try (Connection connection = DriverManager.getConnection(url, user, password)) {
//
//        }

        SpringApplication.run(WordLinkApplication.class, args);
    }
}
