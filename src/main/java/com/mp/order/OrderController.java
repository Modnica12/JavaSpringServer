package com.mp.order;

import com.mp.PizzaServiceImpl;
import com.mp.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<Order> orders = orderService.findAll();
        System.out.println(orders);
        return orders;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<Order> getOrder(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @RequestMapping(value = "/make", method = RequestMethod.GET)
    @ResponseBody
    public String makeOrder(){
        order();
        return "Now we handle your order.";
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public void deletePizza(@PathVariable Long id){
        orderService.deleteById(id);
    }

    private void order(){
        Order newOrder = new Order();
        newOrder.setPizzaIds(new Cart().getAllFromCart());
        System.out.println("A_A_A_A__A_A_A_A_A_A_A__A_A__    " + newOrder.getId() + "\n\n\n");
        orderService.save(newOrder);
    }
}
