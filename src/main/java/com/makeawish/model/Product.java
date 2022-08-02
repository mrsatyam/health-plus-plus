package com.makeawish.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	private int id;
	
	public Product() {
		super();
	}
	
	private String imagePath;

	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", imagePath=" + imagePath + "]";
	}
	
}
