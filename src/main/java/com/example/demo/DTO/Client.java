package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "client")
public class Client{

	@Column(name = "\"name\"")
	private String name;

	@Column(name = "\"discount\"")
	private int discount;

	//@JsonIgnore
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@NonNull
	private int id;

	public Client() {

	}

	@JsonCreator
	public Client(String name, int discount) {
		this.name = name;
		this.discount = discount;
	}

	public Client setName(String name){
		this.name = name;
		return this;
	}

	public String getName(){
		return name;
	}

	public Client setDiscount(int discount){
		this.discount = discount;
		return this;
	}

	public int getDiscount(){
		return discount;
	}

	public Client setId(int id){
		this.id = id;
		return this;
	}

	public int getId(){
		return id;
	}
}