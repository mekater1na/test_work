package com.example.demo.Spring;

import com.example.demo.DTO.*;
import com.example.demo.Hibernate.GettingDataHibernate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class RequestSpring {

    private GettingDataHibernate data = new GettingDataHibernate();

    @GetMapping("/parking/empty")
    public int getEmptyParking() {
        return data.getCountEmptyParking();
    }

    @GetMapping("/parking/all")
    public int getAllParking() {
        return data.getCountAllParking();
    }

    @GetMapping(value = "/client/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Client> getClient(@PathVariable("id") int id) {
        Client client = data.getClientById(id);
        if(client == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(client);
        }
    }

    @GetMapping(value = "/car/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Car> getCar(@PathVariable("id") int id) {
        List<Car> cars = data.getCarInfo();
        List<Car> result = new ArrayList<>();
        for(Car car:cars) {
            if(car.getClient().getId() == id)
            {
                result.add(car);
            }
        }
        return result;
    }

    @GetMapping(value = "/get", produces = "application/json")
    @ResponseBody
    public List<Parking> getFullInfo() {
        List<Parking> parkings = data.getFullInfo();
        return parkings;
    }

    @PostMapping(value = "/post", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Parking postParking(@RequestBody Parking parking) {
        Parking postedParking = data.postParkingSpot(parking);
        return postedParking;
    }

    @PutMapping(value = "/put", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Parking updateParking(@RequestBody Parking parking) {
        Parking updatedParking = data.updateParkingSpot(parking);
        return updatedParking;
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    @ResponseBody
    public String updateParking(@PathVariable("id") int id) {
        return data.deleteParkingSpot(id);
    }
}
