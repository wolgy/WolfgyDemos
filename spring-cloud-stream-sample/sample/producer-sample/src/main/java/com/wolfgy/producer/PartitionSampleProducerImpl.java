package com.wolfgy.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfgy.PartitionSampleProducer;
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
@EnableBinding(value = {PartitionSampleProducer.class})
@Slf4j
public class PartitionSampleProducerImpl {

    @Autowired
    private PartitionSampleProducer producer;


    public void forPartition(String code) throws Exception {
        SampleDto dto = new SampleDto();
        dto.setCode(code);
        dto.setDesc(String.valueOf(System.currentTimeMillis()));
        dto.setName("forPartition");
        boolean isSuccess = producer.forPartition().send(MessageBuilder.withPayload(dto).setHeader("partition",code).build());
        log.info("{} send msg {}.Content:{}","forPartition",isSuccess?"successfully":"failed",new ObjectMapper().writeValueAsString(dto));
    }

    public void forPartitionExtractor(String code) throws Exception {
        SampleDto dto = new SampleDto();
        dto.setCode(code);
        dto.setDesc(String.valueOf(System.currentTimeMillis()));
        dto.setName("forPartitionExtractor");
        boolean isSuccess = producer.forPartitionExtractor().send(MessageBuilder.withPayload(dto).setHeader("partition",code).build());
        log.info("{} send msg {}.Content:{}","forPartition",isSuccess?"successfully":"failed",new ObjectMapper().writeValueAsString(dto));
    }
}
