package com.saurav.projectpagination.repository;

import com.saurav.projectpagination.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
