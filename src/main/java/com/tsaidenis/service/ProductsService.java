package com.tsaidenis.service;

import com.tsaidenis.model.ProductId;
import com.tsaidenis.model.Products;

import java.util.List;

public interface ProductsService {
    Products findById(ProductId id);

    List<Products> findAll();

    void delete(Products products);

    void create(Products products);

    void update(Products products);
}
