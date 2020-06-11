package com.wolfgy.controller;

import com.wolfgy.producer.ExchangeTypeTestProducerImpl;
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
@RequestMapping("/exchangeType")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExchangeTypeSampleController {

    private final ExchangeTypeTestProducerImpl exchangeTypeTestProducer;

    @GetMapping("/headers")
    public void headers(@RequestParam String header) throws Exception {
        exchangeTypeTestProducer.forHeaders(header);
    }

    @GetMapping("/direct")
    public void direct(@RequestParam String key) throws Exception {
        exchangeTypeTestProducer.forDirect(key);
    }

    @GetMapping("/topic")
    public void topic() throws Exception {
        exchangeTypeTestProducer.forTopic();
    }
}
