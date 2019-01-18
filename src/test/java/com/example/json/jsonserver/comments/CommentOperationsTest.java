package com.example.json.jsonserver.comments;

import static io.restassured.RestAssured.given;

import java.time.LocalTime;
import java.util.Random;

import org.junit.Test;

import com.example.json.jsonserver.dto.Comments;
import com.example.json.jsonserver.dto.Post;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class CommentOperationsTest {
	
	public Response response;
	public RequestSpecification request;
	public String COMMENTS_URI = "http://ec2-52-91-203-95.compute-1.amazonaws.com:3000/comments";	
	@Test
	public void getAllComments() {
		given().when().get(COMMENTS_URI).then().statusCode(200);
		
	}

	@Test
	public void getCommentsById() {
		given().when().get(COMMENTS_URI + "/1").then().statusCode(200);
		
	}
	
	@Test
	public void modifyCommentById() {
		ResponseBody body = given().when().get(COMMENTS_URI + "/1").body();
		Comments comments = body.as(Comments.class);		
		comments.setBody("new  modified title , current time " + LocalTime.now());		
		request = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(comments);
		request.put(COMMENTS_URI + "/1").then().statusCode(200);
		
	}
	
	@Test
	public void createComments() {
		
		Random rand = new Random();
		
		Comments comments = new Comments();
		comments.setPostId(rand.nextInt(345699));
		comments.setBody("Test Body " + LocalTime.now());
		comments.setEmail("test@test.com");			
		request = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(comments);
		request.post(COMMENTS_URI).then().statusCode(201);
		
	}
	
}
