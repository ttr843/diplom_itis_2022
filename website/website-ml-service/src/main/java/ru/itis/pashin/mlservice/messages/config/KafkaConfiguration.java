package ru.itis.pashin.mlservice.messages.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import ru.itis.pashin.mlservice.messages.util.KafkaTopicConstant;


@Configuration
public class KafkaConfiguration {

    public static final String LOAN_REQUEST_GROUP = "loan-ml-request-group";
    public static final String MAX_POLL_INTERVAL_MS = "max.poll.interval.ms:" + Integer.MAX_VALUE;

    @Bean
    public NewTopic loanRequest() {
        return TopicBuilder.name(KafkaTopicConstant.LOAN_REQUEST_TOPIC)
                .config(TopicConfig.RETENTION_MS_CONFIG, "86400000")//1 day
                .partitions(1)
                .build();
    }

    @Bean
    public NewTopic loanResponse() {
        return TopicBuilder.name(KafkaTopicConstant.LOAN_RESPONSE_TOPIC)
                .config(TopicConfig.RETENTION_MS_CONFIG, "86400000")//1 day
                .partitions(1)
                .build();
    }
}
