package ru.itis.pashin.website.indexsync;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.itis.pashin.website.index.common.properties.SearchIndexProperties;

@SpringBootApplication(
        scanBasePackages = {
                "ru.itis.pashin.website.common.model",
                "ru.itis.pashin.website.common.service",
                "ru.itis.pashin.website.index.common",
                "ru.itis.pashin.website.indexsync"
        }
)
@EnableConfigurationProperties({SearchIndexProperties.class})
@EntityScan(basePackages = "ru.itis.pashin.website.common.model")
@EnableJpaRepositories(basePackages = {"ru.itis.pashin.website.common.service.repository"})
public class IndexSyncApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(IndexSyncApp.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
