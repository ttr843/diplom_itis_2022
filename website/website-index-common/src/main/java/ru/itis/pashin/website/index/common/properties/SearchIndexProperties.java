package ru.itis.pashin.website.index.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
@ConfigurationProperties("index")
public class SearchIndexProperties {
    private String elasticHost;
}
