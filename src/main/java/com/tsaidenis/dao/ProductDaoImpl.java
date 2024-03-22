package com.tsaidenis.dao;

import com.tsaidenis.HibernateSessionFactoryUtil;
import com.tsaidenis.model.Products;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductDaoImpl implements ProductDao{
    @Override
    public void create(Products products) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(products);
            transaction.commit();
        }
    }
}
