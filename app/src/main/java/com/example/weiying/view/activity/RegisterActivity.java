package com.example.weiying.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.weiying.R;
import com.example.weiying.model.bean.RegisterBean;
import com.example.weiying.presenter.RegisterPresenter;
import com.example.weiying.view.interfaces.IRegisterView;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements IRegisterView, View.OnClickListener {

    private EditText et_register_name;
    private EditText et_register_pass;
    private EditText et_register_pass2;
    private EditText et_register_nickname;
    private RadioGroup sex_rg;
    private Button btn_register;

    private String sex="1";

    private RegisterBean registerBean=new RegisterBean();

    @Override
    void initView() {
        et_register_name = findViewById(R.id.et_register_name);
        et_register_pass = findViewById(R.id.et_register_pass);
        et_register_pass2 = findViewById(R.id.et_register_pass2);
        et_register_nickname = findViewById(R.id.et_register_nickname);
        sex_rg = findViewById(R.id.sex_rg);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);
    }

    @Override
    void initData() {
        sex_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.sex_nan:
                        sex="1";
                        break;
                    case R.id.sex_nv:
                        sex="2";
                        break;
                }
            }
        });
    }

    @Override
    public void onSuccess(Object success) {
        registerBean = (RegisterBean) success;
        if (!TextUtils.isEmpty(registerBean.getMessage())){
            Toast.makeText(RegisterActivity.this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
            if (registerBean.getStatus().equals("0000")){
                Intent intent = getIntent();
                intent.putExtra("phone",et_register_name.getText().toString());
                intent.putExtra("pwd",et_register_pass.getText().toString());
                setResult(2,intent);
                finish();
                return;
            }
        }
        et_register_name.setEnabled(true);
        et_register_pass.setEnabled(true);
        et_register_pass2.setEnabled(true);
        et_register_nickname.setEnabled(true);
        sex_rg.setEnabled(true);
        btn_register.setEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                String phone = et_register_name.getText().toString();
                String pwd = et_register_pass.getText().toString();
                String pwd2 = et_register_pass2.getText().toString();
                String nickName = et_register_nickname.getText().toString();
//                if(!CommonUtil.isMobileNO(phoneNum)) {
//                    Toast.makeText(RegisterActivity.this,"手机号格式不正确",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(!CommonUtil.isPassNO(pass)) {
//                    Toast.makeText(RegisterActivity.this,"密码格式不正确",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(getPresenter() != null) {
                    et_register_name.setEnabled(false);
                    et_register_pass.setEnabled(false);
                    et_register_pass2.setEnabled(false);
                    et_register_nickname.setEnabled(false);
                    sex_rg.setEnabled(false);
                    btn_register.setEnabled(false);
                    HashMap<String,String> map = new HashMap<>();
                    map.put("phone",phone);
                    map.put("pwd",pwd);
                    map.put("pwd2",pwd2);
                    map.put("nickName",nickName);
                    map.put("sex",sex);
                    getPresenter().getDataFromServer(map);
                }
                break;
        }
    }

    @Override
    RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }

    @Override
    int setChildContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
    }
}
