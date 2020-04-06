package com.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepo;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to KerellPizza";
    }

    @RequestMapping(value = "/pizzas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Pizza> getPizzas() {
        return pizzaRepo.getAllPizzas();
    }

    @RequestMapping(value = "/pizza",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Pizza addPizza(@RequestBody Pizza pizza){
        return pizzaRepo.addPizza(pizza);
    }


    @RequestMapping(value = "/pizza",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Pizza updatePizza(@RequestBody Pizza pizza){
        return pizzaRepo.updatePizza(pizza);
    }

    @RequestMapping(value = "/pizza/{pizName}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deletePizza(@PathVariable("pizName") String pizzaName){
        pizzaRepo.deletePizza(pizzaName);
    }
}