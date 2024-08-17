/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.order.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data()
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productId;
    private Integer quantity;
    private Double price;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem item)) return false;

        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
