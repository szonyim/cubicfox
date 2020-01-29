package com.cubicfox.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(unique = true)
    public String username;

    /**
     * Excluded from json serialization
     */
    @Column(nullable = false)
    @JsonIgnore
    public String password;

    @Column(nullable = false)
    public String email;

    @JsonBackReference
    @OneToMany(targetEntity = Product.class, mappedBy = "user")
    public Collection<Product> products;

    @JsonBackReference
    @OneToMany(targetEntity = Rate.class, mappedBy = "user")
    public Collection<Rate> rates;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

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

    public Collection<Rate> getRates() {
        return rates;
    }

    public void setRates(Collection<Rate> rates) {
        this.rates = rates;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
