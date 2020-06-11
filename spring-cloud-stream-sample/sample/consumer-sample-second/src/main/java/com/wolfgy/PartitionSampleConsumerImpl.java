package com.wolfgy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfgy.dto.SampleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;

/**
 * @author wolfgy
 */
@Slf4j
@EnableBinding(value = {PartitionSampleConsumer.class})
public class PartitionSampleConsumerImpl {

    @StreamListener(value = ExchangeNames.PARTITION_TEST)
    public void forPartition(@Payload SampleDto dto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(dto);
        log.info("===>{} has received a msg:{}", ExchangeNames.PARTITION_TEST, jsonStr);
    }
    @StreamListener(value = ExchangeNames.PARTITION_EXTRACTOR_TEST)
    public void forPartitionExtractor(@Payload SampleDto dto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(dto);
        log.info("===>{} has received a msg:{}", ExchangeNames.PARTITION_EXTRACTOR_TEST, jsonStr);
    }
}
