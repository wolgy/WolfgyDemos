package com.wolfgy;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author wolfgy
 */
public interface SampleProducer {

    /**
     * producer forHeader
     * @return MessageChannel
     */
    @Output(ExchangeNames.EXCHANGE_TYPE_HEADERS)
    MessageChannel forHeaders();

    /**
     * producer forDirect
     * @return MessageChannel
     */
    @Output(ExchangeNames.EXCHANGE_TYPE_DIRECT)
    MessageChannel forDirect();

    /**
     * producer forTopic
     * @return MessageChannel
     */
    @Output(ExchangeNames.EXCHANGE_TYPE_DEFAULT_TOPIC)
    MessageChannel forTopic();
}
