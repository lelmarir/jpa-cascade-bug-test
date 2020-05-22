package com.example;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Child {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer id;

	public Child() {
		// hibernate
	}
	@Override
	public String toString() {
		return "Child["+id+"]";
	}
}
