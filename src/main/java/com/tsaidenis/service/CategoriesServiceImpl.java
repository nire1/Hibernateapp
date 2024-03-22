package com.tsaidenis.service;

import com.tsaidenis.dao.CategoriesDaoImpl;
import com.tsaidenis.model.Categories;

public class CategoriesServiceImpl implements CategoriesService{
    private CategoriesDaoImpl categoriesDao = new CategoriesDaoImpl();
    @Override
    public Categories findById(Long id) {
        return categoriesDao.findById(id);
    }
}
