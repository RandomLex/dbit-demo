package com.dbit.app;

import com.dbit.model.Title;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Start {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        EntityManager em = sessionFactory.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Title title = em.find(Title.class, 1);

        System.out.println("!!!" + title);

        tx.commit();
        sessionFactory.close();

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//
//        Title title = session.find(Title.class, 1);
//        System.out.println("!!!" + title);
//
//        tx.commit();
//        session.close();
    }
}
