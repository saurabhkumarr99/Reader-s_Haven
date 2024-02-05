package com.haven.postgress.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private LocalDateTime lastAccessedTime;

	public UserSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserSession(String userId, LocalDateTime lastAccessedTime) {
		super();
		this.userId = userId;
		this.lastAccessedTime = lastAccessedTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime getLastAccessedTime() {
		return lastAccessedTime;
	}

	public void setLastAccessedTime(LocalDateTime lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

	@Override
	public String toString() {
		return "UserSession [userId=" + userId + ", lastAccessedTime=" + lastAccessedTime + "]";
	}

}
