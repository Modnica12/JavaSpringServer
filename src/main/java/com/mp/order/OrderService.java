package com.mp.order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();
    Optional<Order> getById(Long id);
    Order save(Order pizza);
    void deleteById(Long id);
}
