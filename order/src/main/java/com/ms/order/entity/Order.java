/*
 * Copyright (c) 2024.
 *
 * @author Bhagwat Kumar
 */

package com.ms.order.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data()
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String couponCode;
    private String customerId;

    @CreationTimestamp
    private Date dateCreated;
    @UpdateTimestamp
    private Date lastUpdated;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> items = new HashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;

        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
