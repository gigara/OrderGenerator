package com.ordergeneration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator {
    // simulator URL
    private final WebClient webClient = WebClient.create("https://gigara.info");
    private final ArrayList<Order> orders = new ArrayList<>();
    private String callBackURL = null;

    int[] items = {2, 3, 4, 5};
    int[] itemProbs = {50, 25, 13, 6};
    int[] quantities = {2, 3};
    int[] quantitiesProbs = {33, 3};

    public OrderGenerator() {
    }

    // generate random order
    public void generate() throws JsonProcessingException {
        // 20% chance
        boolean true20 = new Random().nextInt(5) == 0;
        if (true20) {
            // get data from simulator
            JsonNode node = this.webClient.get().uri("/userFiles/items.json")
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            JsonNode itemDetails = node != null ? node.get("items") : null;
            if (itemDetails != null) {
                ObjectMapper objectMapper = new ObjectMapper();

                // generate random item count
                ArrayList<OrderItem> orderItems = new ArrayList<>();
                int itemCount = getRandom(items, itemProbs);
                for (int i = 1; i <= itemCount; i++) {
                    if (itemDetails.get(i) != null) {
                        Item item = objectMapper.readValue(itemDetails.get(i).toString(), Item.class);

                        // random quantity
                        int quantity = getRandom(quantities, quantitiesProbs);
                        orderItems.add(new OrderItem(item, quantity));
                    }
                }

                // save generated order
                Order order = new Order(orders.size(), OrderStatus.NEW, orderItems);
                orders.add(order);

                // notify the server
                if (callBackURL != null) {
                    notifyServer(order);
                }
            }
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrder(int orderID) {
        for (Order order : orders) {
            if (order.getOrderID() == orderID) return order;
        }
        return null;
    }

    public boolean updateStatus(int orderID, JsonNode body) {
        JsonNode status = body.get("status");
        // check request body
        if (status == null ||
                (!status.asText().equals(OrderStatus.NEW.toString())
                        && !status.asText().equals(OrderStatus.PROCESSING.toString())
                        && !status.asText().equals(OrderStatus.COMPLETED.toString()))) return false;

        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                order.setOrderStatus(OrderStatus.valueOf(status.asText()));
                return true;
            }
        }
        return false;
    }

    public boolean register(JsonNode body) {
        JsonNode ip = body.get("address");
        if (ip != null) {
            callBackURL = ip.asText();
            return true;
        }
        return false;
    }

    private void notifyServer(Order order) {
        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // send POST request
        ResponseEntity<Void> response = restTemplate.postForEntity(callBackURL, order, Void.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful");
        } else {
            System.out.println("Request Failed");
        }
    }

    private int getRandom(int[] items, int[] probs) {
        int position = new Random().nextInt(100);
        for (int i = 0; i < probs.length; i++) {
            if (position < probs[i]) {
                return items[i];
            }
            position -= probs[i];
        }
        return 1;
    }
}
