package com.marinara.wordlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WordLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordLinkApplication.class, args);
    }
}
