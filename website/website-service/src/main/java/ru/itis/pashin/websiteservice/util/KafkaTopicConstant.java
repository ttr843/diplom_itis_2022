package ru.itis.pashin.websiteservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaTopicConstant {

    public static final String LOAN_REQUEST_TOPIC = "loan.request.topic";
    public static final String LOAN_RESPONSE_TOPIC = "loan.response.topic";
}
