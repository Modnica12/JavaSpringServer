package com.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaServiceImpl implements PizzaService{

    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public List<Pizza> findAll(){
        List<Pizza> list = new ArrayList<>();
        pizzaRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Optional<Pizza> getById(Long id){
        return pizzaRepository.findById(id);
    }

    @Override
    public Pizza save(Pizza pizza){
        return pizzaRepository.save(pizza);
    }
    @Override
    public void deleteById(Long id){
        pizzaRepository.deleteById(id);
    }
}
