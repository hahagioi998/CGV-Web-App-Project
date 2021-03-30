package com.example.cgvapplication.service;

import com.example.cgvapplication.model.user.User;
import com.example.cgvapplication.service.dto.CMRespDto;
import com.example.cgvapplication.service.dto.user.FindPasswordRespDto;
import com.example.cgvapplication.service.dto.user.FindUsernameRespDto;
import com.example.cgvapplication.service.dto.user.UpdatePasswordReqDto;
import com.example.cgvapplication.service.dto.user.UserUpdateReqDto;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @POST("/findUsername")
    Call<CMRespDto<FindUsernameRespDto>> findUsername(@Body FindUsernameRespDto fIndPasswordRespDto);

    @POST("/findPassword")
    Call<CMRespDto<FindPasswordRespDto>> findPassword(@Body FindPasswordRespDto fIndPasswordRespDto);

    @PUT("/changePassword")
    Call<CMRespDto<UpdatePasswordReqDto>> changePassword(@Body UpdatePasswordReqDto updatePasswordReqDto);

    @GET("/user")
    Call<CMRespDto<User>> findById();

    @PUT("/user")
    Call<CMRespDto<User>> updateById(@Body UserUpdateReqDto userUpdateReqDto);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://112.162.114.11:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
