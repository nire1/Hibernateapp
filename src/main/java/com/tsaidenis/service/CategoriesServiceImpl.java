package com.tsaidenis.service;

import com.tsaidenis.dao.CategoriesDaoImpl;
import com.tsaidenis.model.Categories;

import java.util.List;

public class CategoriesServiceImpl implements CategoriesService{
    private CategoriesDaoImpl categoriesDao = new CategoriesDaoImpl();

    @Override
    public Categories findByName(String name) {
        return categoriesDao.findByName(name);
    }

    @Override
    public Categories findById(Long id) {
        return categoriesDao.findById(id);
    }

    @Override
    public List<Categories> findAll() {
        return categoriesDao.findAll();
    }

    @Override
    public void delete(Categories categories) {
        categoriesDao.delete(categories);
    }

    @Override
    public void create(Categories categories) {
        categoriesDao.create(categories);
    }

    @Override
    public void update(Categories categories) {
        categoriesDao.update(categories);
    }
}
