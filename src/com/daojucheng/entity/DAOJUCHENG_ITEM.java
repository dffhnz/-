package com.daojucheng.entity;

public class DAOJUCHENG_ITEM {
    private int id;
    private int categoryId;
    private String name;
    private int price;
    private String image;
    private String itemdesc;
    private int inventory;
    public DAOJUCHENG_ITEM(int id, int categoryId, String name, int price, String image,String itemdesc,int inventory) {
        super();
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.image = image;
        this.itemdesc = itemdesc;
        this.inventory = inventory;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
    public int getId() {
        return id;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getImage() {
        return image;
    }
    public String getItemdesc() {
        return itemdesc;
    }
    public int getInventory() {
        return inventory;
    }
}
