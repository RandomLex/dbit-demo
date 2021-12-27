package com.dbit.app.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class EntityManagerHelper {
    private final SessionFactory factory;

    private EntityManagerHelper() {
        Configuration cfg = new Configuration().configure();
        factory = cfg.buildSessionFactory();
    }

    private static class EntityManagerHelperHolder {
        private static final EntityManagerHelper HOLDER_INSTANCE = new EntityManagerHelper();
    }

    public static EntityManagerHelper getInstance() {
        return EntityManagerHelper.EntityManagerHelperHolder.HOLDER_INSTANCE;
    }


    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
