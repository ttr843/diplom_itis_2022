package ru.itis.pashin.website.indexwrite;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(
        scanBasePackages = {
                "ru.itis.pashin.website.common.model",
                "ru.itis.pashin.website.index.common",
                "ru.itis.pashin.website.indexwrite"
        }
)
public class IndexWriteApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(IndexWriteApp.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
