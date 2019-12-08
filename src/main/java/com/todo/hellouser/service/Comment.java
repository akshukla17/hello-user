package com.todo.hellouser.service;

public class Comment {

	private int id;
	private int postId;
	private String email;
	private String body;
	
	public Comment() {
		super();
	}
	public Comment(int id, int postId, String email, String body) {
		super();
		this.id = id;
		this.postId = postId;
		this.email = email;
		this.body = body;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", postId=" + postId + ", email=" + email + ", body=" + body + "]";
	}
}