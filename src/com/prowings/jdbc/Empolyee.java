package com.prowings.jdbc;

public class Empolyee {
	
	int id;
	String name;
	String address;
	
	
	public Empolyee() {
		super();
	}


	public Empolyee(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}


	@Override
	public String toString() {
		return "Empolyee [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	
	

}
