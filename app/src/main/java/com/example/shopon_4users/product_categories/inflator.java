package com.example.shopon_4users.product_categories;

public class inflator {
    String pro,UID;

    public inflator()
    {

    }

    public inflator(String pro, String UID) {
        this.pro = pro;
        this.UID = UID;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
