package com.cubicfox.entity;

import org.hibernate.tuple.entity.EntityMetamodel;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id;

    @Column()
    public int code;

    @Column(nullable = false)
    public String name;

    @Column(length = 2000)
    public String description;

    @Column(nullable = false)
    public float price;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) throws Exception {
        if(price <= 0){
            throw new Exception("Invalid price value! Price must be greater then 0!");
        }

        this.price = price;
    }
}
