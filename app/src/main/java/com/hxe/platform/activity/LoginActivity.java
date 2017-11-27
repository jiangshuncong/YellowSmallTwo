package com.hxe.platform.activity;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yellowsmalltwo.R;
import com.hxe.platform.base.BaseActivity;
import com.hxe.platform.custom.GroupControlLogin;
import com.hxe.platform.persenter.LoginPersenter;
import com.hxe.platform.user.LoginUser;
import com.hxe.platform.view.LoginView;

import java.util.ArrayList;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.internal.util.LinkedArrayList;
import retrofit2.Call;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginView {


    private GroupControlLogin groupControlLogin;
    private Button btn_verification;
    private Button btn_verification2;
    private int time;
    private TextView tv_other_login;
    private LinearLayout rv_verification;
    private LinearLayout rv_verification3;
    private int num = 0;
    private ImageView btn_verification3;
    private ImageView btn_verification4;
    private EventHandler eventHandler;
    private EditText et_userphone;
    private Button btn_login;
    private EditText et_verification;
    private EditText et_verification3;
    private String username;
    private String usercode;
    private String userpass;
    private LoginPersenter loginPersenter;

    @Override
    public boolean Immersion() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        // 如果希望在读取通信录的时候提示用户，可以添加下面的代码，并且必须在其他代码调用之前，否则不起作用；如果没这个需求，可以不加这行代码
        //SMSSDK.setAskPermisionOnReadContact(boolShowInDialog)
        initview();

        loginPersenter = new LoginPersenter(this);

        //处理自己的逻辑
        eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                if(data instanceof Throwable){
                    Throwable throwable = (Throwable) data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastStart(msg);
                        }
                    });
                }else{
                    if(event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //处理自己的逻辑
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               // Toast.makeText(LoginActivity.this,"短信验证成功！！",Toast.LENGTH_SHORT).show();
                                ToastStart("短信验证成功！！");
                            }
                        });

                    }else if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               // Toast.makeText(LoginActivity.this,"校验成功！！",Toast.LENGTH_SHORT).show();
                                ToastStart("校验成功！！");
                                //跳转操作
                                IntentStart(MainActivity.class,false);

                            }
                        });
                    }
                }
            }
        };
        SMSSDK.registerEventHandler(eventHandler);


    }

    private void initview() {
        groupControlLogin = findViewById(R.id.gcl_login);

        //获取验证码按钮点击事件
        btn_verification = groupControlLogin.findViewById(R.id.btn_verification);
        btn_verification2 = groupControlLogin.findViewById(R.id.btn_verification2);
        btn_verification.setOnClickListener(this);
        et_userphone = groupControlLogin.findViewById(R.id.et_userphone);

        //常规登录方式
        tv_other_login = groupControlLogin.findViewById(R.id.tv_other_login);
        tv_other_login.setOnClickListener(this);

        //验证码登录控件
        rv_verification = groupControlLogin.findViewById(R.id.rv_verification);
        rv_verification3 = groupControlLogin.findViewById(R.id.rv_verification3);
        et_verification3 = groupControlLogin.findViewById(R.id.et_verification3);

        //密码登录页面显示隐藏密码控件
        btn_verification3 = groupControlLogin.findViewById(R.id.btn_verification3);
        btn_verification4 = groupControlLogin.findViewById(R.id.btn_verification4);
        btn_verification3.setOnClickListener(this);
        btn_verification4.setOnClickListener(this);

        //登录按钮
        btn_login = groupControlLogin.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        //输入框
        et_verification = groupControlLogin.findViewById(R.id.et_verification);
    }

    //获取验证码按钮点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_verification:
                time = 30;
                username = et_userphone.getText().toString().trim();
                if(username.equals("")){
                    ToastStart("请输入手机号！！");
                    return;
                }else{
                    System.out.println("手机号："+username);
                    btn_verification.setVisibility(View.INVISIBLE);
                    btn_verification2.setVisibility(View.VISIBLE);
                    handler.postDelayed(runnable,0);
                }

                SMSSDK.getVerificationCode("+86",username);

                break;

            case R.id.tv_other_login:
                    if(num % 2 == 0){
                        rv_verification.setVisibility(View.INVISIBLE);
                        rv_verification3.setVisibility(View.VISIBLE);
                    }else{
                        rv_verification.setVisibility(View.VISIBLE);
                        rv_verification3.setVisibility(View.INVISIBLE);
                    }
                    num++;
                break;

            case R.id.btn_verification3:
                btn_verification3.setVisibility(View.INVISIBLE);
                btn_verification4.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_verification4:
                btn_verification4.setVisibility(View.INVISIBLE);
                btn_verification3.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_login:

                username = et_userphone.getText().toString().trim();
                usercode = et_verification.getText().toString().trim();
                userpass = et_verification3.getText().toString().trim();

               /* if(trim.equals("")){
                    //Toast.makeText(LoginActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                    //return;
                    ToastStart("请输入手机号");
                }else if(trim1.equals("")){
//                    Toast.makeText(LoginActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
//                    return;
                    ToastStart("请输入验证码");
                }*/

               if(num%2 == 0){
                   SMSSDK.submitVerificationCode("+86", username, usercode);
               }else{
                   loginPersenter.Login(username,userpass);
               }
                break;
        }
    }

    //handler实现倒计时
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
                time -- ;
                if(time == 0){
                    btn_verification2.setVisibility(View.INVISIBLE);
                    btn_verification.setVisibility(View.VISIBLE);
                    handler.removeCallbacks(runnable);
                }else{
                    btn_verification2.setText(time+"s后");
                }
            handler.postDelayed(runnable,1000);
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    //View层
    @Override
    public void LoginSuccess(String code, String msg) {
        System.out.println("LoginSuccess点击登录：code:"+code+msg);
        IntentStart(MainActivity.class,false);
    }

    @Override
    public void LoginError(String code, String msg) {
        System.out.println("LoginError点击登录：code:"+code+msg);
    }

    @Override
    public void LoginFail(Call<LoginUser> call, Throwable t) {
        System.out.println("LoginFail点击登录：t:"+t.toString());
    }
}
