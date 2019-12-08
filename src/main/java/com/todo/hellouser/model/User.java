package com.todo.hellouser.model;

public class User {

	private int id;
	private String username;
	private String name;
	private String email;
	public User() {
		super();
	}
	public User(int id, String username, String name,String email) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.email =email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", email=" + email + "]";
	}
	
}
