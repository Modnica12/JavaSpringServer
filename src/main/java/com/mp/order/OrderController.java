package com.mp.order;

import com.mp.PizzaServiceImpl;
import com.mp.auth.AuthServiceImpl;
import com.mp.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    List<Order> getOrders() {
        return orderService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<Order> getOrder(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @RequestMapping(value = "/make", method = RequestMethod.GET)
    @ResponseBody
    public String makeOrder(){
        return order();
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public void deleteOrder(@PathVariable Long id){
        String username = AuthServiceImpl.getCurrentUser();
        if (!username.isEmpty()) {
            Optional<Order> order = orderService.getById(id);
            if (order.isPresent()) {
                if (order.get().getUser().equals(username))
                    orderService.deleteById(id);
            }
        }
    }

    private String order(){
        String username = AuthServiceImpl.getCurrentUser();
        if (!username.isEmpty()) {
            Order newOrder = new Order();
            newOrder.setPizzaIds(new Cart().getAllFromCart());
            newOrder.setUser(username);
            orderService.save(newOrder);
            return "Now we handle your order.";
        }
        else return "You are not authorized";
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public @ResponseBody
    List<Order> getOrdersOfUser() {
        String username = AuthServiceImpl.getCurrentUser();
        if (!username.isEmpty())
            return orderService.getAllWithUsername(AuthServiceImpl.getCurrentUser());
        else return null;
    }
}
