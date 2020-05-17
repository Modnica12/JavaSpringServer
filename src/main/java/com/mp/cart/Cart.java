package com.mp.cart;

import com.mp.Pizza;
import com.mp.PizzaServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private static ArrayList<Pizza> pizzaList = new ArrayList<>();

    public ArrayList<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(ArrayList<Pizza> pizzaList) {
        Cart.pizzaList = pizzaList;
    }

    public void addToCart(Pizza pizza){
        pizzaList.add(pizza);
    }

    public void removeFromCart(Long id){
        pizzaList.removeIf(pizza -> (pizza.getId().equals(id)));
    }

    public ArrayList<Long> getAllFromCart(){
        ArrayList<Long> list = getIds();
        pizzaList.clear();
        return list;
    }

    private ArrayList<Long> getIds(){
        ArrayList<Long> Ids = new ArrayList<>();
        pizzaList.forEach(pizza -> Ids.add(pizza.getId()));
        System.out.println("IDS                 " + Ids);
        return Ids;
    }
}
