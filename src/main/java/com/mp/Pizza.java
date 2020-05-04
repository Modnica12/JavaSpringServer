package com.mp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pizzaName;

    private int doughType;

    private int size;

    public static HashMap<Integer, String> typeMap = new HashMap<Integer, String>(){{
        put(0, "Classic");
        put(1, "thin");
    }};

    public Pizza(){
    }

    public Pizza(String pizzaName, int doughType, int size){
        this.pizzaName = pizzaName;
        this.doughType = doughType;
        this.size = size;
    }

    public Long getId(){
        return id;
    }

    public String getPizzaName(){
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public int getDoughType(){
        return doughType;
    }

    public void setDoughType(int doughType) {
        this.doughType = doughType;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Pizza [id=" + id + ", pizzaName=" + pizzaName +
                ", doughType=" + doughType + ", size=" + size  + "]";
    }
}
