package com.jmbullion.demoapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "payment_discount")
public class PaymentDiscount {

    @Id
    private int id;

    @Column
    private String paymentName;

    @Column
    private double discount;
}
