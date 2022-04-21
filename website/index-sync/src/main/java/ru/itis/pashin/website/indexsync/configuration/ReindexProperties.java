package ru.itis.pashin.website.indexsync.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("reindex")
public class ReindexProperties {

    private Integer threadPoolSize = 4;
    private Integer rebuildPageSize = 1000;
    private Boolean rebuildLoan;
    private Boolean reindexLoan;
    private Integer loanSavedBefore = 0;
    private Integer loanStartingPage = 0;
}
