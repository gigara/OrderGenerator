package com.ordergeneration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator {
    private final WebClient webClient = WebClient.create("https://gigara.info");
    private ArrayList<Order> orders = new ArrayList<>();

    int[] items = {2, 3, 4, 5};
    int[] itemProbs = {50, 25, 13, 6};
    int[] quantities = {2, 3};
    int[] quantitiesProbs = {33, 3};

    public OrderGenerator() {
    }

    // generate random order
    public void generate() throws JsonProcessingException {
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
            orders.add(new Order(orders.size(), OrderStatus.NEW, orderItems));
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
