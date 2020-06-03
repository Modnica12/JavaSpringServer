package com.mp.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query("select order from Order order where order.userName=:username")
    List<Order> getAllWithUsername(@Param("username") String username);
}
