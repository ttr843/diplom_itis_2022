package ru.itis.configservice;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ConfigApp.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
