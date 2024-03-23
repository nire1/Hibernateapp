package com.tsaidenis.dao;

import com.tsaidenis.model.Categories;

import java.util.List;

public interface CategoriesDao {
    Categories findById(Long id);

    List<Categories> findAll();

    void delete(Categories categories);

    void create(Categories categories);
    void deleteAll();
    Categories findByName(String name);
    void update(Categories categories);
}
