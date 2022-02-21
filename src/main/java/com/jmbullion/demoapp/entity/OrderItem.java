package com.jmbullion.demoapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {

    @Id
    private int itemId;

    @Column
    private String itemName;

    @Column
    private double price;
}
