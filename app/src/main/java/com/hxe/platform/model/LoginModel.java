package com.hxe.platform.model;

import com.hxe.platform.Internetserver.RetrofitRequestServer;
import com.hxe.platform.Internetserver.URL;
import com.hxe.platform.user.LoginUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 蒋順聪 on 2017/11/23.
 */

public class LoginModel {

        public void Login(String username,String userpassword){
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(URL.LOGIN)
                        .build();

                RetrofitRequestServer retrofitRequestServer = retrofit.create(RetrofitRequestServer.class);
                Call<LoginUser> getlogin = retrofitRequestServer.getlogin(username, userpassword);
                getlogin.enqueue(new Callback<LoginUser>() {
                        @Override
                        public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                            //System.out.println("返回请求结果："+response.body().getMsg());
                                String code = response.body().getCode();
                                String msg = response.body().getMsg();
                                System.out.println("code---------------"+code);
                                System.out.println("msg---------------"+msg);
                                if(code.equals("0")){
                                        iLogin.LoginSuccess(code,msg);
                                }else{
                                        iLogin.LoginError(code,msg);
                                }
                                System.out.println("请求成功："+msg);

                        }

                        @Override
                        public void onFailure(Call<LoginUser> call, Throwable t) {
                                System.out.println("请求错误：："+t.toString());
                                iLogin.LoginFail(call,t);
                        }
                });
        }

        public ILogin iLogin;

        public void setiLogin(ILogin iLogin) {
                this.iLogin = iLogin;
        }

        public interface ILogin{
                void LoginSuccess(String code,String msg);
                void LoginError(String code,String msg);
                void LoginFail(Call<LoginUser> call, Throwable t);
        }

}
