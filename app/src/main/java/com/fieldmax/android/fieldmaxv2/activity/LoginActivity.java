package com.fieldmax.android.fieldmaxv2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fieldmax.android.fieldmaxv2.R;

/**
 * Created by suraj on 10/29/2015.
 */
public class LoginActivity extends AppCompatActivity {
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mLoginButton=(Button)findViewById(R.id.loginbutton);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,ReportActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}