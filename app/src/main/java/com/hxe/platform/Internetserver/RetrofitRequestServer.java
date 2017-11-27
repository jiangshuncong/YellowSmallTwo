package com.hxe.platform.Internetserver;

import com.hxe.platform.user.LoginUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 蒋順聪 on 2017/11/23.
 */

public interface RetrofitRequestServer {

    @GET("user/login")
    Call<LoginUser> getlogin(@Query("mobile")String mobile,@Query("password")String password);

}
