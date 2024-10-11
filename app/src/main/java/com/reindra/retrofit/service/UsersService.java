package com.reindra.retrofit.service;

import com.reindra.retrofit.model.User;
import com.reindra.retrofit.model.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsersService {
    @GET("users")
    public Call<List<UsersResponse>> getTitle();

    @FormUrlEncoded
    @POST("users")
    Call<UsersResponse> createUser(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("avatar") String avatar);
}