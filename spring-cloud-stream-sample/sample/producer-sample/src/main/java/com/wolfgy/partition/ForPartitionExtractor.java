package com.wolfgy.partition;

import org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy;
import org.springframework.cloud.stream.binder.PartitionSelectorStrategy;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 77025331
 */
@Component("partitionExtractor")
public class ForPartitionExtractor implements PartitionKeyExtractorStrategy, PartitionSelectorStrategy {
    @Override
    public int selectPartition(Object key, int divisor) {
        return Integer.parseInt(((Map<String, String>)key).get("partition"));
    }

    @Override
    public Object extractKey(Message<?> message) {
        return message.getHeaders();
    }
}
