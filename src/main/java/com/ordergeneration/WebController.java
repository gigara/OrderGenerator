package com.ordergeneration;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebController {
    OrderGenerator generator = new OrderGenerator();

    @RequestMapping(value = "/generateOrder", method = RequestMethod.GET)
    public ResponseEntity<?> generateOrder() throws JsonProcessingException {
        generator.generate();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Order> getOrders() {
        return generator.getOrders();
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Order getOrderDetails(@PathVariable int id) {
        return generator.getOrder(id);
    }

    @RequestMapping(value = "orders/{id}/updateStatus", method = RequestMethod.PUT)
    public void setOrderStatus(@PathVariable String id, @RequestBody String description) {

    }

    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public void registration(@RequestBody String description) {
        System.out.println("Service is " + description);
//        itemsDetails.RegisterService(description);
    }

    @RequestMapping(value = "/step", method = RequestMethod.PUT)
    public void Step(@RequestBody String step) {
        System.out.println(step);
//        itemsDetails.ClockSync();
    }
}
