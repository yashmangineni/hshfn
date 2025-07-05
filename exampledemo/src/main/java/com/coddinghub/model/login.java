package com.coddinghub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="exampledemo")
public class login {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String email;
	private Long password;
	@Override
	public String toString() {
		return "login [id=" + id + ", email=" + email + ", password=" + password + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPassword() {
		return password;
	}
	public void setPassword(Long password) {
		this.password = password;
	}
	

}
