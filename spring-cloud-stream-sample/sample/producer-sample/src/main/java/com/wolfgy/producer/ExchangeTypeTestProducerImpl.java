package com.wolfgy.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfgy.ExchangeTypeSampleProducer;
import com.wolfgy.dto.SampleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 不同exchange type的生产者使用示例。
 * Producer samples of different exchange type
 * @author wolfgy
 */
@EnableBinding(value = {ExchangeTypeSampleProducer.class})
@Slf4j
public class ExchangeTypeTestProducerImpl {

    @Autowired
    private ExchangeTypeSampleProducer producer;

    public void forHeaders(String header) throws Exception {
        SampleDto dto = new SampleDto();
        dto.setCode("code1");
        dto.setDesc(String.valueOf(System.currentTimeMillis()));
        dto.setName("forHeader");
        boolean isSuccess = producer.forHeaders().send(MessageBuilder.withPayload(dto).setHeader("key1",header).build());
        log.info("{} send msg {}.Content:{}","forHeader",isSuccess?"successfully":"failed",new ObjectMapper().writeValueAsString(dto));
    }

    public void forDirect(String key) throws Exception {
        SampleDto dto = new SampleDto();
        dto.setCode("code1");
        dto.setDesc(String.valueOf(System.currentTimeMillis()));
        dto.setName("forDirect");
        boolean isSuccess = producer.forDirect().send(MessageBuilder.withPayload(dto).setHeader("routeTo", key).build());
        log.info("{} send msg {}.Content:{}","forDirect",isSuccess?"successfully":"failed",new ObjectMapper().writeValueAsString(dto));
    }

    public void forTopic() throws Exception {
        SampleDto dto = new SampleDto();
        dto.setCode("code");
        dto.setDesc(String.valueOf(System.currentTimeMillis()));
        dto.setName("forTopic");
        boolean isSuccess = producer.forTopic().send(MessageBuilder.withPayload(dto).build());
        log.info("{} send msg {}.Content:{}","forTopic",isSuccess?"successfully":"failed",new ObjectMapper().writeValueAsString(dto));
    }
}
