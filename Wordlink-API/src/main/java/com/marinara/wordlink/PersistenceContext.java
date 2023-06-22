package com.marinara.wordlink;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class PersistenceContext {

    @Bean
    public DSLContext dsl() throws SQLException {
        String user = "root";
        String password = "Password123";
        String url = "jdbc:mysql://localhost:3306/wordlink?autoReconnect=true&useSSL=false";

        return DSL.using(DriverManager.getConnection(url, user, password), SQLDialect.MYSQL);
    }

}
