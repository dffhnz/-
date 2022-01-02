package com.daojucheng.entity;


public class DAOJUCHENG_SHOPPING {
    private int id;
    private String category;
    private int iid;
    private String name;
    private String image;
    private int price;
    private String uid;
    private int num;
    private int status;
    public DAOJUCHENG_SHOPPING(int id,String category,int iid,String name,String image,int price,String uid,int num,int status){
        this.id=id;
        this.category=category;
        this.iid=iid;
        this.image=image;
        this.name=name;
        this.price=price;
        this.num=num;
        this.uid=uid;
        this.status=status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public int getIid() {
        return iid;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public String getUid() {
        return uid;
    }

    public int getNum() {
        return num;
    }
}

