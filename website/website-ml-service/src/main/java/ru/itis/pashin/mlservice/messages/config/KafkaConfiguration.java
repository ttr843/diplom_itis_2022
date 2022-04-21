package ru.itis.pashin.mlservice.messages.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import ru.itis.pashin.mlservice.messages.util.KafkaTopicConstant;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Configuration
public class KafkaConfiguration {

    public static final String LOAN_REQUEST_GROUP = "loan-request-group";
    public static final String MAX_POLL_INTERVAL_MS = "max.poll.interval.ms:" + Integer.MAX_VALUE;

    @Bean
    public NewTopic loanRequest() {
        return TopicBuilder.name(KafkaTopicConstant.LOAN_REQUEST_TOPIC)
                .partitions(1)
                .build();
    }
}
