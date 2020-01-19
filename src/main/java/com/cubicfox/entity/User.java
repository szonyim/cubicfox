package com.cubicfox.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @javax.persistence.Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id;


}
