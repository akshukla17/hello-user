package com.todo.hellouser.model;

public class Post {

	private int id;
	private int userId;
	private String title;
	
	public Post() {
		super();
	}
	public Post(int id, int userId, String title) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", userId=" + userId + ", title=" + title + "]";
	}
	
}
