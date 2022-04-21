package ru.itis.pashin.website.indexwrite.messages.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaTopicConstant {
    public static final String LOAN_INDEX_WRITE_TOPIC = "loan.index.write.topic";
}
