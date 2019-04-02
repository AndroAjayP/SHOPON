package com.example.shopon_4users.cart_and_payement;

class inflatorOfCart {
    String brand,imagurl,Price,size,User_uid,Vendor_uid;


    public inflatorOfCart() {

    }

    public inflatorOfCart(String brand, String imagurl, String price, String size, String user_uid,String vendor_uid ) {
        this.brand = brand;
        this.imagurl = imagurl;
        this.Price = price;
        this.size = size;
        User_uid = user_uid;
        Vendor_uid = vendor_uid;
       // this.Sum = String.valueOf(Sum+Integer.parseInt(Price));

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImagurl() {
        return imagurl;
    }

    public void setImagurl(String imagurl) {
        this.imagurl = imagurl;
    }


    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public String getUser_uid() {
        return User_uid;
    }

    public void setUser_uid(String user_uid) {
        User_uid = user_uid;
    }

    public String getVendor_uid() {
        return Vendor_uid;
    }

    public void setVendor_uid(String vendor_uid) {
        Vendor_uid = vendor_uid;
    }






}

