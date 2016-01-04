package com.fieldmax.android.fieldmaxv2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fieldmax.android.fieldmaxv2.R;
import com.fieldmax.android.fieldmaxv2.maps.BeatsActivity;

/**
 * Created by suraj on 11/2/2015.
 */
public class InboxActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        Button footerCustomerButton = (Button) findViewById(R.id.FooterButtonInbox);
        footerCustomerButton.setSelected(true);

        Button footerButton = (Button) findViewById(R.id.FooterButtonCustomer);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(InboxActivity.this, CustomerListActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });


        footerButton = (Button) findViewById(R.id.FooterButtonHome);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(InboxActivity.this, ReportActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });

        footerButton = (Button) findViewById(R.id.FooterButtonBeat);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(InboxActivity.this, BeatsActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });


    }
}