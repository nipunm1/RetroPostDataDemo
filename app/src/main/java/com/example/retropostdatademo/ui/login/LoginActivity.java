package com.example.retropostdatademo.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retropostdatademo.R;
import com.example.retropostdatademo.api.MyRetroClient;
import com.example.retropostdatademo.data.model.req.LoginReq;
import com.example.retropostdatademo.data.model.res.Body;
import com.example.retropostdatademo.data.model.res.LoginRes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    ProgressBar loadingProgressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         usernameEditText = findViewById(R.id.username);
         passwordEditText = findViewById(R.id.password);
         loginButton = findViewById(R.id.login);
         loadingProgressBar = findViewById(R.id.loading);

         passwordEditText.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(charSequence.length()>2){
                        loginButton.setEnabled(true);
                    }
                    else{
                        loginButton.setEnabled(false);
                    }
             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
    }
     public void login(String uname,String pass){
         LoginReq loginReq = new LoginReq();
         loginReq.setPass(pass);
         loginReq.setUname(uname);
         MyRetroClient.api.login(loginReq).enqueue(new Callback<LoginRes>() {
             @Override
             public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                 LoginRes loginRes = response.body();
                 if(loginRes.getStatus()){
                     Body body = loginRes.getBody();
                     Toast.makeText(LoginActivity.this,"Welcome "+body.getFName()+" "+body.getLName(),Toast.LENGTH_LONG).show();
                 }else{
                     Toast.makeText(LoginActivity.this, "incorrect user", Toast.LENGTH_LONG).show();
                 }
                 loadingProgressBar.setVisibility(View.GONE);
             }

             @Override
             public void onFailure(Call<LoginRes> call, Throwable t) {
                loadingProgressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "error  :"+t.getMessage(), Toast.LENGTH_LONG).show();
             }
         });
     }


}
