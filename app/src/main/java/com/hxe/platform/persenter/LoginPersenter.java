package com.hxe.platform.persenter;

import com.hxe.platform.base.BaseActivity;
import com.hxe.platform.model.LoginModel;
import com.hxe.platform.user.LoginUser;
import com.hxe.platform.view.LoginView;

import retrofit2.Call;

/**
 * Created by 蒋順聪 on 2017/11/23.
 */

public class LoginPersenter extends BaseActivity implements LoginModel.ILogin{

    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPersenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel();
        loginModel.setiLogin(this);
    }

    public void Login(String username, String userpassword){


        if(username.equals("")){
            ToastStart("请输入手机号");
        }else if(userpassword.equals("")){
            ToastStart("请输入密码");
        }

        loginModel.Login(username,userpassword);

    }

    @Override
    public boolean Immersion() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void init() {

    }

    @Override
    public void LoginSuccess(String code, String msg) {
        loginView.LoginSuccess(code,msg);
    }

    @Override
    public void LoginError(String code, String msg) {
        loginView.LoginError(code, msg);
    }

    @Override
    public void LoginFail(Call<LoginUser> call, Throwable t) {
        loginView.LoginFail(call, t);
    }
}
