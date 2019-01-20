package com.example.json.jsonserver.dto;

public class Users {
	
	String name;
	String username;
	String email;
	
	String phone;
	String website;
	
	private Address address;
	    
	public Users() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	 
	
	


}
