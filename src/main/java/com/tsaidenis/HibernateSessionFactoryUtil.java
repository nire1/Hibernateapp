package com.tsaidenis;

import com.tsaidenis.model.Categories;
import com.tsaidenis.model.Products;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
//    private static StandardServiceRegistry registry;
    private static Logger logger = LogManager.getLogger(HibernateSessionFactoryUtil.class);

    public HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            try{
//                registry = new StandardServiceRegistryBuilder().configure().build();
//
//                MetadataSources sources = new MetadataSources(registry);
//                Metadata metadata = sources.getMetadataBuilder().build();
//                sessionFactory=metadata.getSessionFactoryBuilder().build();

                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Categories.class);
                configuration.addAnnotatedClass(Products.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            }catch (Exception e){
//                e.printStackTrace();
//                if (registry != null) {
//                    StandardServiceRegistryBuilder.destroy(registry);
//                }

                logger.error(e);
            }
        }
        return sessionFactory;
    }
}
