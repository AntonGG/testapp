package com.microimpuls.test.testapp;

public abstract class BasicUserInfo {
	private final int userId;
	private final String username;

	public BasicUserInfo( int userId, String username ) {
		this.userId = userId;
		this.username = username;
	}

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}
}
