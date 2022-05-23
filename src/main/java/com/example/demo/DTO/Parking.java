package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

@Entity
@Table(name = "parking")
public class Parking{

	@Column(name = "\"overrun\"")
	private String overrun;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"carId\"")
	private Car car;

	@Column(name = "\"available\"")
	private String available;

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	public Parking() {

	}

	@JsonCreator
	public Parking(int id, Car car, String available, String  overrun) {
		this.id = id;
		this.available = available;
		this.car = car;
		this.overrun = overrun;
	}

	public Parking setOverrun(String overrun){
		this.overrun = overrun;
		return this;
	}

	public String getOverrun(){
		return overrun;
	}

	public Parking setCar(Car car){
		this.car = car;
		return this;
	}

	public Car getCar(){
		return car;
	}

	public Parking setAvailable(String available){
		this.available = available;
		return this;
	}

	public String getAvailable(){
		return available;
	}

	public Parking setId(int id){
		this.id = id;
		return this;
	}

	public int getId(){
		return id;
	}
}