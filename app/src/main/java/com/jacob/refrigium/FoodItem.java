package com.jacob.refrigium;

import java.util.Date;

/**
 * Created by jacob on 7/23/15.
 */

public class FoodItem {
    private int icon;
    private String name;
    private Date expirationDate;

    public FoodItem(int icon, String name, Date expirationDate) {
        this.icon = icon;
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
