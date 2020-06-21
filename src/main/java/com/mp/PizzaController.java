package com.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class PizzaController {

    @Autowired
    private PizzaServiceImpl pizzaService;

    @RequestMapping("/")
    @ResponseBody
    public String welcome(@RequestParam("token") Optional<String> token) {
        return token.orElse("Welcome to KerellPizza");
    }

    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public @ResponseBody List<Pizza> getPizzas() {
        List<Pizza> pizzas = pizzaService.findAll();
        System.out.println(pizzas);
        return pizzas;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView redirectReg() {
        return new ModelAndView("redirect:" + "http://localhost:8081/");
    }

    @RequestMapping("/auth")
    @ResponseBody
    public ModelAndView redirectAuth() {
        return new ModelAndView("redirect:" + "http://localhost:8081/");
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