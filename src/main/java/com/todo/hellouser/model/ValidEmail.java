package com.todo.hellouser.model;

import java.util.List;

public class ValidEmail {

	private List<String> emailList;

	public ValidEmail(List<String> emailList) {
		super();
		this.emailList = emailList;
	}

	public List<String> getEmailList() {
		return emailList;
	}

	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}
}
