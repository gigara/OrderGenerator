package com.ordergeneration;

public class ItemsDetails {

 String iD;
 String itemName;
 String supplier;
 int weight;
 String order;

    public ItemsDetails(String iD, String itemName, String supplier, int weight, String order) {
        this.iD = iD;
        this.itemName = itemName;
        this.supplier = supplier;
        this.weight = weight;
        this.order = order;
    }

    public ItemsDetails(String iD, String itemName, String supplier, int weight) {
        this.iD = iD;
        this.itemName = itemName;
        this.supplier = supplier;
        this.weight = weight;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "ItemsDetails{" +
               "iD='" + iD + '\'' +
               ", itemName='" + itemName + '\'' +
               ", supplier='" + supplier + '\'' +
               ", weight=" + weight +
               '}';
    }

    public String getOrderDetails(String id ){
        return id;
    }
}
