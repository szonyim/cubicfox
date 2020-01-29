package com.cubicfox.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Collection;
import java.util.function.Function;


@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Min(100000)
    @Max(999999)
    public int code;

    @Column(nullable = false)
    public String name;

    @Column(length = 2000)
    public String description;

    @Column(nullable = false)
    public float price;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public User user;

    @OneToMany(targetEntity = Rate.class, mappedBy = "product")
    public Collection<Rate> rates;

    @Transient
    public Float avgRate;

    @Transient
    static Function<Collection<Rate>, Float> avgRateFunc = (Function<Collection<Rate>, Float> & Serializable) rates -> {
        float avg = 0;
        if( rates != null && rates.isEmpty() == false) {
            for (Rate r : rates) {
                avg += r.getRateValue();
            }

            avg = avg / rates.size();
        }

        return avg;
    };

    @PostLoad
    public void PostLoad(){
        avgRate = Product.avgRateFunc.apply(this.getRates());
    }

    //<editor-fold desc="Constructors">
    public Product() {
    }

    public Product(int code, String name, String description, float price, User user) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.user = user;
    }
    //</editor-fold>

    //<editor-fold desc="Getter / Setter">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Rate> getRates() {
        return rates;
    }

    public void setRates(Collection<Rate> rates) {
        this.rates = rates;
    }
    //</editor-fold>
}
