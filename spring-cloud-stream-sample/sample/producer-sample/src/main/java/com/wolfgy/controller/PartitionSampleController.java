package com.wolfgy.controller;

import com.wolfgy.producer.ExchangeTypeTestProducerImpl;
import com.wolfgy.producer.PartitionSampleProducerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 不同exchange type的生产者调用入口
 * Producer samples of different exchange type
 * @author wolfgy
 */
@RestController
@RequestMapping("/partition")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PartitionSampleController {

    private final PartitionSampleProducerImpl producer;

    @GetMapping("/partition")
    public void partition(@RequestParam String code) throws Exception {
        producer.forPartition(code);
    }

    @GetMapping("/partitionExtractor")
    public void forPartitionExtractor(@RequestParam String code) throws Exception {
        producer.forPartitionExtractor(code);
    }
}
