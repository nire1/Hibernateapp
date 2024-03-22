package com.tsaidenis.dao;

import com.tsaidenis.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriesDaoImplTest {
    ProductDaoImpl productDao = new ProductDaoImpl();
    CategoriesDaoImpl categoriesDao = new CategoriesDaoImpl();
@BeforeEach
void prepare(){
    categoriesDao.deleteAll();
}

    @Test
    void findById() {
        categoriesDao.create(new Categories("Шорты",10L));
        categoriesDao.create(new Categories("Рубаха",11L));
        categoriesDao.create(new Categories("Чай",15L));
        List<Categories>categories = categoriesDao.findAll();
        ProductId id = new ProductId("Штаны зеленые", (long) 12);
        Products products1 = new Products(id, 200.00, Condition.NEW,
                new Manufacturer("company name", "RUSSIA"), categories.get(1));
        productDao.create(products1);
        Products products2 = new Products(new ProductId("Штаны красные", (long) 12), 200.00, Condition.NEW,
                new Manufacturer("company name", "RUSSIA"), categories.get(1));
        productDao.create(products2);
        Categories categories1 = categoriesDao.findById(categories.get(1).getId());

        assertEquals(categories.get(1).getName(),categories1.getName());

    }
    @Test
    void del(){
        categoriesDao.create(new Categories("asd",10L));
        categoriesDao.create(new Categories("asd",11L));
        categoriesDao.create(new Categories("qwe",15L));
        List<Categories>categories = categoriesDao.findAll();
    }
}