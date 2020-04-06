package com.mp;

import java.util.ArrayList;

public class Pizza {
    public String pizzaName;
    public String doughType;
    public Integer size;
    public ArrayList<String> ingredients;

    public Pizza(){
    }

    public Pizza(String pizzaName, String doughType, Integer size){
        this.pizzaName = pizzaName;
        this.doughType = doughType;
        this.size = size;
    }

    public String getPizzaName(){
        return pizzaName;
    }

    public String getDoughType(){
        return doughType;
    }

    public Integer getSize(){
        return size;
    }

    public ArrayList<String> getIngredients(){
        return ingredients;
    }

}
