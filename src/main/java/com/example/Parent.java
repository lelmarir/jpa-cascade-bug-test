package com.example;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Parent {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer id;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Child> childs = new ArrayList<>();

	public Parent() {
		// hibernate
	}

	public List<Child> getChilds() {
		return childs;
	}

	@Override
	public String toString() {
		return "Parent[" + id + "]";
	}

}
