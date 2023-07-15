package com.nocountry.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @Column(name = "category_uuid")
    private String id;
    @Column(name = "name", nullable = false,unique = true)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name="state",nullable = false)
    private Boolean state ;
    public Category(String name,String description ,String number ,Boolean state){
        this.id= UUID.randomUUID().toString();
        this.name=name;
        this.description=description;
        this.number=number;
        this.state=state;
    }

}
