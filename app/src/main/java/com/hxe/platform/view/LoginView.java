package com.hxe.platform.view;

import com.hxe.platform.user.LoginUser;

import retrofit2.Call;

/**
 * Created by 蒋順聪 on 2017/11/23.
 */

public interface LoginView {

    void LoginSuccess(String code,String msg);
    void LoginError(String code,String msg);
    void LoginFail(Call<LoginUser> call, Throwable t);

}
