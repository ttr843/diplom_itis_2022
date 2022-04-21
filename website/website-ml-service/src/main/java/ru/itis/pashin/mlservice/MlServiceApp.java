package ru.itis.pashin.mlservice;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "ru.itis.pashin.website.common.model",
                "ru.itis.pashin.website.common.service",
                "ru.itis.pashin.mlservice"
        }
)
@EntityScan(basePackages = "ru.itis.pashin.website.common.model")
@EnableJpaRepositories(basePackages = {"ru.itis.pashin.website.common.service.repository"})
public class MlServiceApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(MlServiceApp.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
