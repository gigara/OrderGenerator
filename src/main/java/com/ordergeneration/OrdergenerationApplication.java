package com.ordergeneration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@SpringBootApplication
public class OrdergenerationApplication {
    private static final String clockServerURL = "https://webhook.site/238010f5-d07b-40dc-a039-bef2d27eb1cb";

    public static void main(String[] args) {
        SpringApplication.run(OrdergenerationApplication.class, args);

        // notify the clock server
        RestTemplate restTemplate = new RestTemplate();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "Order Generator Service");
        hashMap.put("url", "http://localhost:8080/generateOrder");

        // send request
        ResponseEntity<Void> response = restTemplate.postForEntity(clockServerURL, hashMap, Void.class);
    }

}
