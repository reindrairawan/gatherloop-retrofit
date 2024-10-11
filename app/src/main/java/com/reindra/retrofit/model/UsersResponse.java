package com.reindra.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class UsersResponse{

	@SerializedName("password")
	private String password;

	@SerializedName("role")
	private String role;

	@SerializedName("creationAt")
	private String creationAt;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("avatar")
	private String avatar;

	@SerializedName("email")
	private String email;

	@SerializedName("updatedAt")
	private String updatedAt;

	public String getPassword(){
		return password;
	}

	public String getRole(){
		return role;
	}

	public String getCreationAt(){
		return creationAt;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getAvatar(){
		return avatar;
	}

	public String getEmail(){
		return email;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}