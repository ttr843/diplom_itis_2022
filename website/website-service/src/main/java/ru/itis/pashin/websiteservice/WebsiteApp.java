package ru.itis.pashin.websiteservice;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "ru.itis.pashin.website.common.model",
                "ru.itis.pashin.website.common.service",
                "ru.itis.pashin.websiteservice"
        }
)
@EntityScan(basePackages = "ru.itis.pashin.website.common.model")
@EnableJpaRepositories(basePackages = {"ru.itis.pashin.website.common.service.repository"})
public class WebsiteApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(WebsiteApp.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
