package com.example.demo.Hibernate;

import org.hibernate.Session;

public class HibernateSession {

    private static Session session;

    public Session start() {
        session = HibernateSessionFactoryCreator.createSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }

    public void close() {
        session.getTransaction().commit();
        session.close();
    }
}
