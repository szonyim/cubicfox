package com.cubicfox.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Table(name = "users")
public class User implements Serializable {

    @javax.persistence.Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(unique = true)
    public String username;

    public String password;

    @OneToMany(targetEntity = Product.class)
    public Collection<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }
}
