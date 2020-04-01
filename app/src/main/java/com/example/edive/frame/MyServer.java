package com.example.edive.frame;

import com.example.edive.bean.FindPasswordBean;
import com.example.edive.bean.LoginBean;
import com.example.edive.bean.RegisterBean;
import com.example.edive.bean.VerificationBean;
import com.example.edive.bean.VerificationLgoinBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyServer {
    String url = "http://28f1w59522.zicp.vip:55937/";


    @POST("sso/login?")
    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded","Authorization:Basic ZG1kOjEyMzQ1Ng=="})
    Observable<LoginBean> getPasswordLogin(@Field("grant_type")String type, @Field("username")String username, @Field("password")String password,@Field("loginType")String loginType);

    @POST("sso/mobile?")
    @FormUrlEncoded
    @Headers({"Authorization:Basic ZG1kOjEyMzQ1Ng==","deviceId:007"})
    Observable<VerificationLgoinBean> getVerLogin(@Field("code")String code, @Field("mobile") String mobile,@Field("loginType") String loginType);

    @GET("sso/getAuthCode?")
    @Headers("deviceId:007")
    Observable<VerificationBean> getVerGetcode(@Query("mobile") String mobile);

    @POST("sso/findPassword")
    @Headers("deviceId:007")
    Observable<FindPasswordBean> getFindPassword(@Body RequestBody body);

    @POST("sso/register")
    @FormUrlEncoded
    @Headers("deviceId:007")
    Observable<RegisterBean> getRegisterBean(@Field("username") String username, @Field("password") String password, @Field("authCode") String authCode, @Field("invitationCode") String invitationCode);

    @POST("auth/weixin")
    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded","Authorization:Basic ZG1kOjEyMzQ1Ng=="})
    Observable<LoginBean> getWechatLogin(@Field("code") String code,@Field("loginType") String loginType);
}
