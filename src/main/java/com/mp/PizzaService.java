package com.mp;

import java.util.List;
import java.util.Optional;

public interface PizzaService {
    List<Pizza> findAll();
    Optional<Pizza> getById(Long id);
    Pizza save(Pizza pizza);
    void deleteById(Long id);
}
