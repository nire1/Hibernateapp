package com.tsaidenis.service;

import com.tsaidenis.model.Categories;

import java.util.List;

public interface CategoriesService {
    List<Categories> findAll();
    Categories findById(Long id);
    Categories findByName(String name);
    void delete(Categories categories);
    void create(Categories categories);
    void update(Categories categories);
}
