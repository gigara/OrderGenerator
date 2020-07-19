package com.ordergeneration;

public class Item {

    private String id;
    private String name;
    private String supplier;
    private int weight;

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ItemsDetails{" +
                "iD='" + id + '\'' +
                ", itemName='" + name + '\'' +
                ", supplier='" + supplier + '\'' +
                ", weight=" + weight +
                '}';
    }

    public String getOrderDetails(String id) {
        return id;
    }
}
