package com.ordergeneration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebController {
    OrderGenerator generator = new OrderGenerator();

    /**
     * Generate order
     *
     * @return 200
     * @throws JsonProcessingException request data error
     */
    @RequestMapping(value = "/generateOrder", method = RequestMethod.PUT)
    public ResponseEntity<?> generateOrder() throws JsonProcessingException {
        generator.generate();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * return order details
     *
     * @return order details
     */
    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Order> getOrders() {
        return generator.getOrders();
    }

    /**
     * return order details by order ID
     *
     * @param id order ID
     * @return order details
     */
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Order getOrderDetails(@PathVariable int id) {
        return generator.getOrder(id);
    }

    /**
     * update order details
     *
     * @param id   order id
     * @param body order status
     */
    @RequestMapping(value = "orders/{id}/updateStatus", method = RequestMethod.PUT)
    public ResponseEntity<?> setOrderStatus(@PathVariable int id, @RequestBody JsonNode body) {
        return generator.updateStatus(id, body) ?
                new ResponseEntity<>(HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Register for new order listening
     *
     * @param body listening server address
     * @return 202 if success
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registration(@RequestBody JsonNode body) {
        return generator.register(body) ?
                new ResponseEntity<>(HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
