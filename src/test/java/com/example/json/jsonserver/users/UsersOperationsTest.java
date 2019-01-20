package com.example.json.jsonserver.users;

import static io.restassured.RestAssured.given;

import java.time.LocalTime;
import java.util.Random;

import org.junit.Test;

import com.example.json.jsonserver.dto.Address;
import com.example.json.jsonserver.dto.Users;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class UsersOperationsTest{	
	
	public Response response;
	public RequestSpecification request;
	public String USERS_URI = "http://ec2-52-91-203-95.compute-1.amazonaws.com:3000/users";	
	@Test
	public void getAllUsers() {
		given().when().get(USERS_URI).then().statusCode(200);
		
	}

	@Test
	public void getUsersById() {
		given().when().get(USERS_URI + "/1").then().statusCode(200);
		
	}
	
	@Test
	public void modifyUsersById() {
		ResponseBody body = given().when().get(USERS_URI + "/1").body();
		Users users = body.as(Users.class);
		users.setEmail("test@test.com");		
	    request = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(users);
		request.put(USERS_URI + "/1").then().statusCode(200);
		
	}
	
	@Test
	public void createUsers() {
		
		Random rand = new Random();
		
		Users users	= new Users();
		users.setName("New User");
		users.setEmail("test1@test.com");
		Address address = new Address();
		address.setSuite("101");
		address.setStreet("Cumming Street");
		address.setZipCode("3004");
		address.setCity("Atlanta");
		users.setAddress(address);
		request = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(users);
		request.post(USERS_URI).then().statusCode(201);
		
	}
	
	
}