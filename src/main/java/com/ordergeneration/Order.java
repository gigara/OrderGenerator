package com.ordergeneration;

import java.util.ArrayList;

public class Order {
    private int orderID;
    private OrderStatus orderStatus;
    private ArrayList<OrderItem> items;

    public Order(int orderID, OrderStatus orderStatus, ArrayList<OrderItem> items) {
        this.orderID = orderID;
        this.orderStatus = orderStatus;
        this.items = items;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }
}
