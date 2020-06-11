package com.wolfgy;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author wolgy
 */
public interface PartitionSampleConsumer {

    /**
     * consumer forPartition
     * @return SubscribableChannel
     */
    @Input(ExchangeNames.PARTITION_TEST)
    SubscribableChannel forPartition();

    /**
     * consumer forPartitionExtractor
     * @return SubscribableChannel
     */
    @Input(ExchangeNames.PARTITION_EXTRACTOR_TEST)
    SubscribableChannel forPartitionExtractor();
}
