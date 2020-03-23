package com.mp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PizzaController {

    @RequestMapping("/")
    public String pizza(){
        //return new Pizza("Pepperoni", "Thin", 25);
        return "index";
    }
}
