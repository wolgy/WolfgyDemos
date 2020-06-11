package com.wolfgy;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author wolgy
 */
public interface ExchangeTypeSampleConsumer {

    /**
     * consumer forHeader
     * @return SubscribableChannel
     */
    @Input(ExchangeNames.EXCHANGE_TYPE_HEADERS)
    SubscribableChannel forHeaders();

    /**
     * consumer forDirect
     * @return SubscribableChannel
     */
    @Input(ExchangeNames.EXCHANGE_TYPE_DIRECT)
    SubscribableChannel forDirect();

    /**
     * consumer forTopic
     * @return SubscribableChannel
     */
    @Input(ExchangeNames.EXCHANGE_TYPE_DEFAULT_TOPIC)
    SubscribableChannel forTopic();
}
