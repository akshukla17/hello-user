package com.todo.hellouser.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.todo.hellouser.exception.NoDataFoundException;
import com.todo.hellouser.model.Post;
import com.todo.hellouser.model.User;

@Service
public class HelloUserService {

	@Value("${base-url}")
	private String baseUrl;

	public static final String EMAIL_VALIDATION_REGEX="^[A-Za-z0-9_.-]+@[A-Za-z0-9_.-]+$";
	/**
	 * api call to fetch user data by username
	 * 
	 * @param username
	 * @throws URISyntaxException
	 */
	public List<String> findByUserName(String username) throws URISyntaxException {
		URI url = new URI(baseUrl + "/users?username=" + username);

		ResponseEntity<ArrayList<User>> responseEntity = new RestTemplate().exchange(url, HttpMethod.GET,
				updateHeadersAndEntity(), new ParameterizedTypeReference<ArrayList<User>>() {
				});
		ArrayList<User> users = responseEntity.getBody();
		if (!users.isEmpty() && users.size() > 0) {
			User user = users.get(0);
			return fetchAllPostsByUser(user.getId());
		} else {
			throw new NoDataFoundException("No user found with username: "+username);
		}
	}
	/**
	 * api call to fetch all posts by user id
	 * @param userId
	 * @throws URISyntaxException
	 */
	private List<String> fetchAllPostsByUser(int userId) throws URISyntaxException {
		List<String> validEmails = new ArrayList<String>();
		URI url = new URI(baseUrl + "/posts?userId=" + userId);

		ResponseEntity<ArrayList<Post>> responseEntity = new RestTemplate().exchange(url, HttpMethod.GET,
				updateHeadersAndEntity(), new ParameterizedTypeReference<ArrayList<Post>>() {
				});
		ArrayList<Post> listOfPost = responseEntity.getBody();
		if(listOfPost.isEmpty()) {
			throw new NoDataFoundException("No post found for this user");
		}
		for (Post p : listOfPost) {
			fetchAllCommentsOfAPost(validEmails, p.getId());
		}
		return validEmails;
	}

	/**
	 * api call to fetch all comments by post id and store in the list
	 * @param postId
	 * @throws URISyntaxException
	 */
	private void fetchAllCommentsOfAPost( List<String > validEmails,int postId) throws URISyntaxException {
		
		URI url = new URI(baseUrl + "/comments?postId=" + postId);

		ResponseEntity<ArrayList<Comment>> commentResponseEntity = new RestTemplate().exchange(url, HttpMethod.GET,
				updateHeadersAndEntity(), new ParameterizedTypeReference<ArrayList<Comment>>() {
				});
		ArrayList<Comment> listOfComment = commentResponseEntity.getBody();
		for (Comment c : listOfComment) {
			if (isEmailValid(c.getEmail())) {
				validEmails.add(c.getEmail());
			}
		}
	}

	/**
	 * Validate the given email is valid or not
	 * @param email
	 * @return boolean
	 */
	private boolean isEmailValid(String email) {
		Pattern pattern = Pattern.compile(EMAIL_VALIDATION_REGEX);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * adding accept and content media type in header
	 * 
	 * @return httpEntity
	 */

	private HttpEntity<String> updateHeadersAndEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		return entity;
	}

}
