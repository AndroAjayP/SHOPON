package com.example.shopon_4users.product_categories;

public class cartItem_inflator {
    String Shop_name;
    String Address;
    String image;
    String UID;

    public cartItem_inflator()
    {

    }

    public cartItem_inflator(String shop_name, String address, String shop_pic , String UID) {
        Shop_name = shop_name;
        Address = address;
        image = shop_pic;
        this.UID=UID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setShop_name(String shop_name) {
        Shop_name = shop_name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setimage(String shop_pic) {
        image = shop_pic;
    }

    public String getShop_name() {
        return Shop_name;
    }

    public String getAddress() {
        return Address;
    }

    public String getimage() {
        return image;
    }
}
