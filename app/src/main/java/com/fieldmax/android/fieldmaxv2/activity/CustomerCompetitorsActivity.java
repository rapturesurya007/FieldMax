package com.fieldmax.android.fieldmaxv2.activity;

import android.os.Bundle;

import com.fieldmax.android.fieldmaxv2.R;

/**
 * Created by suraj on 11/29/2015.
 */
public class CustomerCompetitorsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_competitors);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("key");
        getSupportActionBar().setTitle(message);
    }
}
