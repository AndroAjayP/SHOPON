package com.example.shopon_4users.Vendor_product_collection;

public class firebase_data_inflator {
    String brand,color,description,imagurl,model,name,price,size,uid;

    public firebase_data_inflator()
    {

    }

    public firebase_data_inflator(String brand, String description, String imagurl, String model, String name, String price, String size, String uid) {
        this.brand = brand.concat(" ").concat(model);
       // this.color = color;
        this.description = description;
        this.imagurl = imagurl;
        this.model = model;
        this.name = name;
        this.price = price;
        this.size = size;
        this.uid = uid;
    }

    public String getBrand() {
        return brand.concat(" ").concat(model);
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagurl() {
        return imagurl;
    }

    public void setImagurl(String imagurl) {
        this.imagurl = imagurl;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
