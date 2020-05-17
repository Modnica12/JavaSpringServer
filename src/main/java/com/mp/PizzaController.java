package com.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping()
public class PizzaController {

    @Autowired
    private PizzaServiceImpl pizzaService;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to KerellPizza";
    }

    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public @ResponseBody List<Pizza> getPizzas() {
        List<Pizza> pizzas = pizzaService.findAll();
        System.out.println(pizzas);
        return pizzas;
    }

    @PostMapping(value = "/pizza",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public Pizza addPizza(@RequestBody Pizza pizza){
        return pizzaService.save(pizza);
    }


    @PutMapping(value = "/pizza",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public Pizza updatePizza(@RequestBody Pizza newPizza){
        return pizzaService.save(newPizza);
    }

    @DeleteMapping(value = "/pizza/{pizId}")
    @ResponseBody
    public void deletePizza(@PathVariable("pizId") Long id){
        pizzaService.deleteById(id);
    }

}