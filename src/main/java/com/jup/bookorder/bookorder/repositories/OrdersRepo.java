package com.jup.bookorder.bookorder.repositories;

import com.jup.bookorder.bookorder.entities.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
public interface OrdersRepo extends CrudRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
