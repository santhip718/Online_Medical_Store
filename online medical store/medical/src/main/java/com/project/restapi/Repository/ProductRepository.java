package com.project.restapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.restapi.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

}
