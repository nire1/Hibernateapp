package com.tsaidenis.service;

import com.tsaidenis.dao.ProductsDaoImpl;
import com.tsaidenis.model.ProductId;
import com.tsaidenis.model.Products;

import java.util.List;

public class ProductServiceImpl implements ProductsService{
    private final ProductsDaoImpl productsDao = new ProductsDaoImpl();
    @Override
    public Products findById(ProductId id) {
        return productsDao.findById(id);
    }

    @Override
    public List<Products> findAll() {
        return productsDao.findAll();
    }

    @Override
    public void delete(Products products) {

        productsDao.delete(products.getId());
    }

    @Override
    public void create(Products products) {
        productsDao.create(products);

    }

    @Override
    public void update(Products products) {

        productsDao.update(products);
    }
}
