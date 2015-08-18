package com.jacob.refrigium;

import java.util.Date;

/**
 * Created by jacob on 7/23/15.
 */

public class FoodItem {
    // TODO: add count to FoodItem
    private int id;
    private String foodType;
    private String name;
    private String expirationDate;

    public FoodItem () {}

    public FoodItem(int id, String foodType, String name, String expirationDate) {
        this.id = id;
        this.foodType = foodType;
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
