package com.tsaidenis.dao;

import com.tsaidenis.model.ProductId;
import com.tsaidenis.model.Products;

import java.util.List;

public interface ProductsDao {
    void create(Products products);
    Products findById(ProductId id);

    List<Products> findAll();

    List<Products> findByCategoryId(Long id);

    void delete(ProductId id);

    void deleteAll();

    void update(Products products);
}
