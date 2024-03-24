package com.tsaidenis.dao;

import com.tsaidenis.HibernateSessionFactoryUtil;
import com.tsaidenis.model.ProductId;
import com.tsaidenis.model.Products;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductsDaoImpl implements ProductsDao {
    @Override
    public void create(Products products) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(products);
            transaction.commit();
        }
    }

    @Override
    public Products findById(ProductId id) {
        Products products;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Products> criteria = builder.createQuery(Products.class);
            Root<Products> root = criteria.from(Products.class);
            criteria.select(root).where(builder.equal(root.get("productName"),id.getProductName()));
            TypedQuery<Products> query = session.createQuery(criteria);
            products = query.getSingleResult();

        }
        return products;
    }

    @Override
    public List<Products> findAll() {
        List<Products> productsList;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            productsList = session.createQuery("from Products", Products.class).getResultList();
        }
        return productsList;
    }

    @Override
    public List<Products> findByCategoryId(Long id) {
        List<Products> list;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
//            CriteriaBuilder builder = session.getCriteriaBuilder();
//            CriteriaQuery<Products> criteria = builder.createQuery(Products.class);
//            Root<Products> root = criteria.from(Products.class);
//            criteria.select(root).where(builder.equal(root.get("")))
//            String hql = "from Products where category_id =:categoriesId";
//            Query query = session.createQuery(hql, Products.class);
//            query.setParameter("categoriesId",id);
//            list = query.getResultList();
            session.beginTransaction();
            Query query = session.createNativeQuery("SELECT * FROM products WHERE category_id =:categoryId", Products.class);
            query.setParameter("categoryId", id).getResultList();
            list = query.getResultList();
        }
        return list;
    }

    @Override
    public void delete(ProductId id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Products product = session.find(Products.class, id);
            session.remove(product);
            transaction.commit();

        }

    }

    @Override
    public void deleteAll() {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query query = session.createNativeQuery("DELETE FROM products", Products.class);
            query.executeUpdate();


        }

    }

    @Override
    public void update(Products products) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(products);
            transaction.commit();
        }
    }
}
