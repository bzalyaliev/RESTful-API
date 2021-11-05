package com.example.socks.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sock")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
