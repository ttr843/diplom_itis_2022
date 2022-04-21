package ru.itis.pashin.mlservice.messages.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaTopicConstant {

    public static final String LOAN_REQUEST_TOPIC = "loan.request.topic";
    public static final String LOAN_RESPONSE_TOPIC = "loan.response.topic";
}
