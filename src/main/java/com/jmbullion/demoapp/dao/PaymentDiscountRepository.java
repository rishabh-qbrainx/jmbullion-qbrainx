package com.jmbullion.demoapp.dao;

import com.jmbullion.demoapp.entity.PaymentDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDiscountRepository extends JpaRepository<PaymentDiscount, Integer> {

    PaymentDiscount findByPaymentName(String paymentName);
}
