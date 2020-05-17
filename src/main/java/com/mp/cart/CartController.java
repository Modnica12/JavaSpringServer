package com.mp.cart;

import com.mp.Pizza;
import com.mp.PizzaServiceImpl;
import com.mp.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    Cart cart = new Cart();

    @Autowired
    PizzaServiceImpl pizzaService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String cartWelcome(){
        return "It's your cart";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    List<Pizza> getPizzasFromCart() {
        return cart.getPizzaList();
    }

    @PutMapping(value = "/add/{pizzaId}")
    @ResponseBody
    public void addPizzaToCart(@PathVariable Long pizzaId){
        Optional<Pizza> pizza = pizzaService.getById(pizzaId);
        pizza.ifPresent(value -> cart.addToCart(value));
    }

    @DeleteMapping(value = "/remove/{id}")
    @ResponseBody
    public void removePizza(@PathVariable Long id){
        cart.removeFromCart(id);
    }
}
