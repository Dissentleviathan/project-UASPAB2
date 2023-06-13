package com.example.pariwisata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    @FormUrlEncoded
    @GET("post")
    Call<ValueData<List<Post>>> getPost();

    @FormUrlEncoded
    @POST("auth/login")
    Call<ValueData<User>> login(@Field("username") String usernme,
                            @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/register")
    Call<ValueData<User>> register(@Field("username") String username,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("post")
    Call<ValueData> addPost(@Field("user_id") String user_id,
                            @Field("foto") String foto,
                            @Field("content") String content);

    @FormUrlEncoded
    @PUT("post")
    Call<ValueData> updatePost(@Field("id") String id,
                               @Field("foto") String foto,
                               @Field("content") String content);

    @POST("post/{id}")
    Call<ValueData> deletePost(@Path("id") String id);
}
