package com.example.demo.Hibernate;

import com.example.demo.DTO.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class GettingDataHibernate {

    private Session session = HibernateSessionFactoryCreator.createSessionFactory().openSession();
    private Query query;

    public int getCountEmptyParking() {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        query = session
                .createNativeQuery("select count(*) from parking where \"available\" = 'true'");
        int count = ((BigInteger) query.getSingleResult()).intValue();
        return count;
    }

    public int getCountAllParking() {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        query = session
                .createNativeQuery("select count(*) from parking");
        int count = ((BigInteger) query.getSingleResult()).intValue();
        return count;
    }

    public Client getClientById(int id) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        List <Client> client = session.createCriteria(Client.class).list();
        try {
            return client.get(id - 1);
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Car> getCarInfo() {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        List<Car> car = session.createCriteria(Car.class).list();
        try {
            return car;
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Parking> getFullInfo() {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        List<Parking> parkings = session.createCriteria(Parking.class).list();
        try {
            return parkings;
        }
        catch (Exception e) {
            return null;
        }
    }

    public Parking postParkingSpot(Parking parking) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        Parking resultParking = new Parking();
        if(session.find(Parking.class, parking.getId()) == null) {
            Client postedClient = new Client()
                    .setId(parking.getCar().getClient().getId())
                    .setName(parking.getCar().getClient().getName())
                    .setDiscount(parking.getCar().getClient().getDiscount());
            Reservation postedReservation = new Reservation()
                    .setId(parking.getCar().getReservation().getId())
                    .setStartTime(parking.getCar().getReservation().getStartTime())
                    .setPrice(parking.getCar().getReservation().getPrice());
            Car postedCar = new Car()
                    .setId(parking.getCar().getId())
                    .setModel(parking.getCar().getModel())
                    .setClient(postedClient)
                    .setReservation(postedReservation);
            Parking postedParking = new Parking()
                    .setId(parking.getId())
                    .setCar(postedCar)
                    .setAvailable(parking.getAvailable())
                    .setOverrun(parking.getOverrun());
            try {
                session.merge(postedParking);
                session.flush();
                resultParking = session.find(Parking.class, parking.getId());
            }
            catch (Exception e) {
                System.out.println("error posting");
            }
        }
        else {
            resultParking = null;
        }
        return resultParking;
    }

    public Parking updateParkingSpot(Parking parking) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        Parking resultParking = new Parking();
        if(session.find(Parking.class, parking.getId()) != null) {
            Client postedClient = new Client()
                    .setId(parking.getCar().getClient().getId())
                    .setName(parking.getCar().getClient().getName())
                    .setDiscount(parking.getCar().getClient().getDiscount());
            Reservation postedReservation = new Reservation()
                    .setId(parking.getCar().getReservation().getId())
                    .setStartTime(parking.getCar().getReservation().getStartTime())
                    .setPrice(parking.getCar().getReservation().getPrice());
            Car postedCar = new Car()
                    .setId(parking.getCar().getId())
                    .setModel(parking.getCar().getModel())
                    .setClient(postedClient)
                    .setReservation(postedReservation);
            Parking postedParking = new Parking()
                    .setId(parking.getId())
                    .setCar(postedCar)
                    .setAvailable(parking.getAvailable())
                    .setOverrun(parking.getOverrun());
            try {
                session.merge(postedParking);
                session.flush();
                resultParking = session.find(Parking.class, parking.getId());
            }
            catch (Exception e) {
                System.out.println("error putting");
            }
        }
        else {
            resultParking = null;
        }
        return resultParking;
    }

    public String deleteParkingSpot(int id) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        Parking parkingForDelete = session.find(Parking.class, id);
        session.delete(parkingForDelete);
        if(session.find(Parking.class, id) != null) {
            return "failure";
        }
        else {
            return "success";
        }
    }
}
