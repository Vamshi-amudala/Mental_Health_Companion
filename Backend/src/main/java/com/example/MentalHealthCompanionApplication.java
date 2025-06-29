package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class MentalHealthCompanionApplication {

    static {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

   
        System.setProperty("JDBC_URL", dotenv.get("JDBC_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

       
        System.setProperty("OPENAI_API_KEY", dotenv.get("OPENAI_API_KEY"));
    }

    public static void main(String[] args) {
        SpringApplication.run(MentalHealthCompanionApplication.class, args);
    }
}
