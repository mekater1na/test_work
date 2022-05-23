package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car{

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"clientId\"")
	private Client client;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"reservationId\"")
	private Reservation reservation;

	@Column(name = "\"model\"")
	private String model;

	@Id
	private String id;

	public Car() {

	}

	@JsonCreator
	public Car(String id, String model, Client client, Reservation reservation) {
		this.id = id;
		this.model = model;
		this.client = client;
		this.reservation = reservation;
	}

	public Car setClient(Client client){
		this.client = client;
		return this;
	}

	public Client getClient(){
		return client;
	}

	public Car setReservation(Reservation reservation){
		this.reservation = reservation;
		return this;
	}

	public Reservation getReservation(){
		return reservation;
	}

	public Car setModel(String model){
		this.model = model;
		return this;
	}

	public String getModel(){
		return model;
	}

	public Car setId(String id){
		this.id = id;
		return this;
	}

	public String getId(){
		return id;
	}
}