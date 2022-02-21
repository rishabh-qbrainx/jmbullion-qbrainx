package com.jmbullion.demoapp.dao;

import com.jmbullion.demoapp.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    OrderItem findByItemId(int itemId);
}
