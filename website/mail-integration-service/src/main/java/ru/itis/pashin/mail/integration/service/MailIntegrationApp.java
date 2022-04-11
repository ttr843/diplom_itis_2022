package ru.itis.pashin.mail.integration.service;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
        scanBasePackages = {
                "ru.itis.pashin.website.common.model",
                "ru.itis.pashin.website.common.service",
                "ru.itis.pashin.mail.integration.service"
        }
)
@EntityScan(basePackages = "ru.itis.pashin.website.common.model")
@EnableJpaRepositories(basePackages = {"ru.itis.pashin.website.common.service.repository"})
@EnableScheduling
public class MailIntegrationApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(MailIntegrationApp.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
