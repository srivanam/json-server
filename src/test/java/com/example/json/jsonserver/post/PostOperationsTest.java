package com.example.json.jsonserver.post;

import static io.restassured.RestAssured.given;

import java.time.LocalTime;
import java.util.Random;

import org.junit.Test;

import com.example.json.jsonserver.dto.Post;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class PostOperationsTest {
	
	public Response response;
	public RequestSpecification request;
	public String POST_URI = "http://ec2-52-91-203-95.compute-1.amazonaws.com:3000/posts";
	
	
	@Test
	public void getAllPost() {
		given().when().get(POST_URI).then().statusCode(200);
		
	}

	@Test
	public void getPostById() {
		given().when().get(POST_URI + "/1").then().statusCode(200);
		
	}
	
	@Test
	public void modifyPostById() {
		ResponseBody body = given().when().get(POST_URI + "/1").body();
		Post post = body.as(Post.class);		
		post.setTitle("new  modified title , current time " + LocalTime.now());		
		request = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(post);
		request.put(POST_URI + "/1").then().statusCode(200);
		
	}
	
	@Test
	public void createPost() {
		
		Random rand = new Random();
		
		Post post = new Post();
		post.setUserId(rand.nextInt(345699));
		post.setBody("Test Body " + LocalTime.now());
		post.setTitle("test title - " + LocalTime.now());			
		request = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(post);
		request.post(POST_URI).then().statusCode(201);
		
	}
	
}
