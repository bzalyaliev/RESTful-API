package com.example.socks.repository;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BatchEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "color")
    String color;
    @Column(name = "cottonPart")
    Integer cottonPart;
    @Column(name = "quantity")
    Integer quantity;
}
