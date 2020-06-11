package com.wolfgy;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author wolfgy
 */
public interface PartitionSampleProducer {

    /**
     * producer forPartition
     * @return MessageChannel
     */
    @Output(ExchangeNames.PARTITION_TEST)
    MessageChannel forPartition();

    /**
     * producer forPartitionExtractor
     * @return MessageChannel
     */
    @Output(ExchangeNames.PARTITION_EXTRACTOR_TEST)
    MessageChannel forPartitionExtractor();
}
