package com.mp.order;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Order(){
    }

    private ArrayList<Long> pizzaIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Long> getPizzaIds() {
        return pizzaIds;
    }

    public void setPizzaIds(ArrayList<Long> pizzaIds) {
        this.pizzaIds = pizzaIds;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", pizzaIds=" + pizzaIds + "]";
    }
}
