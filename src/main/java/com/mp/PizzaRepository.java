package com.mp;


import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PizzaRepository {
    private static final Map<String, Pizza> pizzaMap;

    static {
        pizzaMap = new HashMap<String, Pizza>();
        initPizzas();
    }

    private static void initPizzas() {
        Pizza pepperoni = new Pizza("Pepperoni", "classic", 25);
        Pizza bbq = new Pizza("BBQ", "classic", 35);
        Pizza cheesy = new Pizza("Cheesy", "thick", 25);

        pizzaMap.put(pepperoni.getPizzaName(), pepperoni);
        pizzaMap.put(bbq.getPizzaName(), bbq);
        pizzaMap.put(cheesy.getPizzaName(), cheesy);
    }

    public Pizza getPizza(String pizzaName) {
        return pizzaMap.get(pizzaName);
    }

    public Pizza addPizza(Pizza pizza) {
        pizzaMap.put(pizza.getPizzaName(), pizza);
        return pizza;
    }

    public Pizza updatePizza(Pizza pizza) {
        pizzaMap.put(pizza.getPizzaName(), pizza);
        return pizza;
    }

    public void deletePizza(String pizzaName) {
        pizzaMap.remove(pizzaName);
    }

    public List<Pizza> getAllPizzas() {
        Collection<Pizza> c = pizzaMap.values();
        return new ArrayList<Pizza>(c);
    }
}
