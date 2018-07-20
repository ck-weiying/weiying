package com.example.weiying.view.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weiying.R;
import com.example.weiying.application.MyApp;
import com.example.weiying.model.bean.LoginBean;
import com.example.weiying.presenter.LoginPresenter;
import com.example.weiying.view.interfaces.ILoginView;

import java.util.HashMap;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView, View.OnClickListener {

    private EditText et_login_name;
    private EditText et_login_pass;
    private Button btn_login;
    private Button btn_login_register;

    private LoginBean loginBean=new LoginBean();

    @Override
    void initView() {
        et_login_name = findViewById(R.id.et_login_name);
        et_login_pass = findViewById(R.id.et_login_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_login_register = findViewById(R.id.btn_login_register);

        btn_login_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    void initData() {
        et_login_name.setText(MyApp.sharedPreferences.getString("phone", null));
    }

    @Override
    public void onSuccess(Object success) {
        loginBean = (LoginBean) success;
        Toast.makeText(LoginActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        et_login_name.setEnabled(true);
        et_login_pass.setEnabled(true);
        btn_login.setEnabled(true);
        //  code":"0"    登陆成功
        if (loginBean.getStatus().equals("0000")){
            MyApp.editor
                    .putInt("userId", loginBean.getResult().getId())
                    .putString("nickName",loginBean.getResult().getNickName())
                    .putString("phone",loginBean.getResult().getPhone())
                    .putInt("sex",loginBean.getResult().getSex())
                    .commit();
            MainActivity.start(this);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.btn_login:
                //判断账号密码    是否符合规格
                String phone = et_login_name.getText().toString();
                String pwd = et_login_pass.getText().toString();
//                if(!CommonUtil.isMobileNO(name)) {
//                    Toast.makeText(LoginActivity.this,"手机号格式不正确",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(!CommonUtil.isPassNO(pass)) {
//                    Toast.makeText(LoginActivity.this,"密码格式不正确",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                //发送账号密码验证登录
                if(getPresenter() != null) {
                    et_login_name.setEnabled(false);
                    et_login_pass.setEnabled(false);
                    btn_login.setEnabled(false);
                    HashMap<String,String> map = new HashMap<>();
                    map.put("phone",phone);
                    map.put("pwd",pwd);
                    getPresenter().getDataFromServer(map);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==2){
            et_login_name.setText(data.getStringExtra("phone"));
            et_login_pass.setText(data.getStringExtra("pwd"));
        }
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
    }
}
