package com.jmbullion.demoapp.dao;

import com.jmbullion.demoapp.entity.QuantityDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuantityDiscountRepository extends JpaRepository<QuantityDiscount, Integer> {
    List<QuantityDiscount> findByItemId(int itemId);

}
