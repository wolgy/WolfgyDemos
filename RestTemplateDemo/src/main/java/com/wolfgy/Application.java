package com.wolfgy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wolfgy
 */
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        String username = "003571";
        String groupId = "";
        String postRank = "";
        String categoryCode = "L0-001-00A";
        String token = "af168e2297844fe28cf15e1115f80748";
        /*
        URL
         */
        String baseUrl = "http://xxx.xxx.com";
        String apiUrl = baseUrl+"/school/category?username={username}&groupId={groupId}&postRank={postRank}&categoryCodes={categoryCodes}";
        /*
        HTTP Header
         */
        RestTemplate template = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", token);
        requestHeaders.add("Content-Type", "application/json;charset=UTF-8");
        //HttpEntity存放body及header
        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
        /*
        URL参数
         */
        Map<String, String> httpParam = new HashMap<>();
        httpParam.put("username",username);
        httpParam.put("groupId",groupId);
        httpParam.put("postRank",postRank);
        httpParam.put("categoryCodes",categoryCode);
        /*
        Execute
         */
        ResponseEntity<String> response = template.exchange(apiUrl, HttpMethod.GET, requestEntity, String.class, httpParam);
        log.info("result:{}",response.getBody());
    }
}
