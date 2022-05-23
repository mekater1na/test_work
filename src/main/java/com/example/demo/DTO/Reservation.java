package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation{

	@Column(name = "\"price\"")
	private int price;

	@Column(name = "\"startTime\"")
	private String startTime;

	//@JsonIgnore
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	public Reservation() {

	}

	@JsonCreator
	public Reservation(String startTime, int price) {
		this.startTime = startTime;
		this.price = price;
	}

	public Reservation setPrice(int price){
		this.price = price;
		return this;
	}

	public int getPrice(){
		return price;
	}

	public Reservation setStartTime(String startTime){
		this.startTime = startTime;
		return this;
	}

	public String getStartTime(){
		return startTime;
	}

	public Reservation setId(int id){
		this.id = id;
		return this;
	}

	public int getId(){
		return id;
	}
}