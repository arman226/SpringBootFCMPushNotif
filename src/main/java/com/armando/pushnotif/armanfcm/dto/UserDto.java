package com.armando.pushnotif.armanfcm.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usertbl")
public class UserDto {

	@Id
	@GeneratedValue
	private Long user_id;
	
	private String username;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserDto(Long user_id, String username) {
		super();
		this.user_id = user_id;
		this.username = username;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
