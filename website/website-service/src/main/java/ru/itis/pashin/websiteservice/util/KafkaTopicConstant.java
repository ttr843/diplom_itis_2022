package ru.itis.pashin.websiteservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaTopicConstant {

    public static final String LOAN_REQUEST_TOPIC = "loan.request.topic";
    public static final String LOAN_INDEX_WRITE_TOPIC = "loan.index.write.topic";
    public static final String LOAN_RESPONSE_TOPIC = "loan.response.topic";
}
