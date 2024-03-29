package com.marinara.wordlink;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class PersistenceContext {

    @Bean
    public DSLContext dsl() throws SQLException {
        String dbLocation = System.getenv("DB_ADDR");

        System.out.println("Using DB Location: " + dbLocation);

        String user = "root";
        String password = "Password123";
        String url = "jdbc:mysql://" + dbLocation + ":3306/wordlink?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";

        return DSL.using(DriverManager.getConnection(url, user, password), SQLDialect.MYSQL);
    }

}
