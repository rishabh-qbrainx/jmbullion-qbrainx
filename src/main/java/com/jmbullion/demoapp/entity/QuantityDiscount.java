package com.jmbullion.demoapp.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quantity_discount")
@Data
public class QuantityDiscount {

    @Id
    private int id;

    @Column
    private int itemId;

    @Column
    private int minQty;

    @Column
    private int maxQty;

    @Column
    private double discount;

}
