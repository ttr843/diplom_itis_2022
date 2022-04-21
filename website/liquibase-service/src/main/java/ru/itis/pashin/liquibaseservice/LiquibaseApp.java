package ru.itis.pashin.liquibaseservice;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LiquibaseApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(LiquibaseApp.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
