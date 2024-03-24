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
            Predicate idPredicate = builder.equal(categoriesRoot.get("id"), id);
            criteria.where(idPredicate);
            TypedQuery<Categories> query = session.createQuery(criteria);
            category = query.getSingleResult();
//            category = session.get(Categories.class,id);
        }

        return category;
    }

    @Override
    public List<Categories> findAll() {
        List<Categories> categories;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Categories> query = builder.createQuery(Categories.class);
            Root<Categories> root = query.from(Categories.class);
            root.fetch("products", JoinType.LEFT);
            query.select(root);
            categories = session.createQuery(query).getResultList();

//          categories= session.createQuery("from Categories ", Categories.class).list();


        }
        return categories;
    }

    @Override
    public void delete(Categories categories) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(categories);
            transaction.commit();

        } catch (Exception e) {
        }

    }

    @Override
    public void deleteAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {

            session.beginTransaction();
            Query query = session.createNativeQuery("DELETE FROM categories", Categories.class);
            query.executeUpdate();
//            CriteriaBuilder builder = session.getCriteriaBuilder();
//            CriteriaDelete<Categories> criteria = builder.createCriteriaDelete(Categories.class);
//            criteria.from(Categories.class);
//            session.createQuery(criteria).executeUpdate();

        }
    }

    @Override
    public Categories findByName(String name) {
        Categories categories;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
//            String hql = "from Categories where name =:name";
//            Query query = session.createQuery(hql, Categories.class);
//            query.setParameter("name",name);
//            categories=(Categories) query.getSingleResult();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Categories> criteria = builder.createQuery(Categories.class);
            Root<Categories> root = criteria.from(Categories.class);
            criteria.select(root).where(builder.equal(root.get("name"), name));
            categories = session.createQuery(criteria).getSingleResult();


        }
        return categories;
    }

    @Override
    public void create(Categories categories) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(categories);
            transaction.commit();
        }

    }

    @Override
    public void update(Categories categories) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(categories);
            transaction.commit();

        }

    }
}
