package com.nocountry.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="INVENTORY")
public class Inventory {
    @Id
    @Column(name = "inventory_uuid", nullable = false,unique = true)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "cant", nullable = false)
    private int cant;
    @Column(name = "update_date", nullable = false, updatable = false)
    private LocalDateTime updateDate;

}
