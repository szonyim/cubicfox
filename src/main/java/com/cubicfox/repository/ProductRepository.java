package com.cubicfox.repository;

import com.cubicfox.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByNameLikeOrDescriptionLike(String nameFilter, String descriptionFilter, Pageable pageable);

    Page<Product> findAllByNameContainsOrDescriptionContains(String nameFilter, String descriptionFilter, Pageable pageable);

}
