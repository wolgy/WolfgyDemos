package com.wolfgy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfgy.dto.SampleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;

/**
 * 不同exchange type的消费者使用示例。
 * Consumer samples of different exchange type
 * @author wolfgy
 */
@Slf4j
@EnableBinding(value = {ExchangeTypeSampleConsumer.class})
public class ExchangeTypeHeaderSampleConsumerImpl {

    @StreamListener(value = ExchangeNames.EXCHANGE_TYPE_HEADERS)
    public void forHeaders(@Payload SampleDto dto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(dto);
        log.info("===>{} has received a msg:{}", ExchangeNames.EXCHANGE_TYPE_HEADERS, jsonStr);
    }

    @StreamListener(value = ExchangeNames.EXCHANGE_TYPE_DIRECT)
    public void forDirect(@Payload SampleDto dto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(dto);
        log.info("===>{} has received a msg:{}", ExchangeNames.EXCHANGE_TYPE_DIRECT, jsonStr);
    }

    @StreamListener(value = ExchangeNames.EXCHANGE_TYPE_DEFAULT_TOPIC)
    public void forTopic(@Payload SampleDto dto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(dto);
        log.info("===>{} has received a msg:{}", ExchangeNames.EXCHANGE_TYPE_DEFAULT_TOPIC, jsonStr);
    }
}
