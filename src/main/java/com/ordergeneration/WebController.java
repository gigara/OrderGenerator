package com.ordergeneration;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class WebController {

    private String orderStatus[] = {"NEW","PROCESS","COMPLETED"};
    ArrayList<ItemsDetails> items = new ArrayList<>();
    ItemsDetails itemsDetails = new ItemsDetails(null,null,null,0);
    @RequestMapping(value = "/generateOrder",method = RequestMethod.GET)
    public void generateOrder(){
        itemsDetails.setiD("aaa");
        itemsDetails.setItemName("kk");
        itemsDetails.setSupplier("ss");
        itemsDetails.setWeight(10);
        System.out.println(itemsDetails.getOrder());
        items.add(itemsDetails);
    }
    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public String getOrders(){
        System.out.println(itemsDetails.getItemName());
        return "Hi";
    }

    @RequestMapping(value = "/orders/{id}",method = RequestMethod.GET)
    public String getOrderDetails(@PathVariable String id){
        System.out.println(itemsDetails.getOrderDetails(id));
        return itemsDetails.getOrderDetails(id);
    }

    @RequestMapping(value = "orders/{id}/updateStatus",method = RequestMethod.PUT)
    public void setOrderStatus(@PathVariable String  id, @RequestBody String description){
        itemsDetails.setOrder(description);
    }
    @RequestMapping(value="/registry",method=RequestMethod.POST)
    public void registration(@RequestBody String description){
        System.out.println("Service is "+description);
//        itemsDetails.RegisterService(description);
    }
    @RequestMapping(value = "/step",method = RequestMethod.PUT)
    public void Step(@RequestBody String step){
        System.out.println(step);
//        itemsDetails.ClockSync();
    }
}
