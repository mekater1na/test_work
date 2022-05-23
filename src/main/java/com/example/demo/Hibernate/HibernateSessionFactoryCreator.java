package com.example.demo.Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.example.demo.DTO.*;

public class HibernateSessionFactoryCreator {
    private static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory() {
        if(sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Car.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Parking.class);
                configuration.addAnnotatedClass(Reservation.class);
                sessionFactory =configuration.buildSessionFactory();
            }
            catch (Exception e) {
                System.out.println("SQL Error");
            }
        }
        return sessionFactory;
    }
}
