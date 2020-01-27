package com.cubicfox.repository;

import com.cubicfox.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
