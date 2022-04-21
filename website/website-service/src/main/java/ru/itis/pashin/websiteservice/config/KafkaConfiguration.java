package ru.itis.pashin.websiteservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import ru.itis.pashin.websiteservice.util.KafkaTopicConstant;


@Configuration
public class KafkaConfiguration {

    public static final String LOAN_RESPONSE_GROUP = "loan-ml-response-group";
    public static final String MAX_POLL_INTERVAL_MS = "max.poll.interval.ms:" + Integer.MAX_VALUE;

    @Bean
    public NewTopic loanRequest() {
        return TopicBuilder.name(KafkaTopicConstant.LOAN_REQUEST_TOPIC)
                .partitions(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "86400000")//1 day
                .build();
    }

    @Bean
    public NewTopic loanIndexWrite() {
        return TopicBuilder.name(KafkaTopicConstant.LOAN_INDEX_WRITE_TOPIC)
                .partitions(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "86400000")//1 day
                .build();
    }

    @Bean
    public NewTopic loanResponse() {
        return TopicBuilder.name(KafkaTopicConstant.LOAN_RESPONSE_TOPIC)
                .partitions(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "86400000")//1 day
                .build();
    }
}
