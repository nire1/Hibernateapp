package com.tsaidenis.dao;

import com.tsaidenis.HibernateSessionFactoryUtil;
import com.tsaidenis.model.Categories;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategoriesDaoImpl implements CategoriesDao {
    @Override
    public Categories findById(Long id) {
        Categories category;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Categories> criteria = builder.createQuery(Categories.class);
            Root<Categories> categoriesRoot = criteria.from(Categories.class);
            Predicate idPredicate = builder.equal(categoriesRoot.get("id"),id);
            criteria.where(idPredicate);
            TypedQuery<Categories> query = session.createQuery(criteria);
            category = query.getSingleResult();
        }

        return category;
    }

    @Override
    public List<Categories> findAll() {
        List<Categories> categories;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Categories>query = builder.createQuery(Categories.class);
            Root<Categories>root = query.from(Categories.class);
            root.fetch("products", JoinType.LEFT);
            query.select(root);
            categories = session.createQuery(query).getResultList();
        }
        return categories;
    }

    @Override
    public void delete(Categories categories) {

    }

    @Override
    public void deleteAll() {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){

            session.beginTransaction();
            Query query = session.createNativeQuery("DELETE FROM categories");
            query.executeUpdate();
        }
    }

//    @Override
//    public Categories findByName(String name) {
//        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
//            Transaction transaction = session.beginTransaction();
//            Query query = session.getNamedQuery("Categories.byName");
//            session.
//        }
//        return null;
//    }

    @Override
    public void create(Categories categories) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(categories);
            transaction.commit();
        }

    }
}
