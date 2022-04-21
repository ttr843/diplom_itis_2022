package ru.itis.pashin.websiteservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import ru.itis.pashin.websiteservice.util.KafkaTopicConstant;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic loanRequest() {
        return TopicBuilder.name(KafkaTopicConstant.LOAN_REQUEST_TOPIC)
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
