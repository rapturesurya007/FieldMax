package com.fieldmax.android.fieldmaxv2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fieldmax.android.fieldmaxv2.R;
import com.fieldmax.android.fieldmaxv2.maps.BeatsActivity;


public class CustomerDashBoardActivity extends BaseActivity {
    private LinearLayout mSalesOrderLayout,mPaymentLayout,mPocketMISLayout,mCompetitorsLayout,mEnquiryLayout,mComplaintsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerdashboard);

        Button footerButtonCustomer = (Button) findViewById(R.id.FooterButtonCustomer);
        footerButtonCustomer.setSelected(true);


        Button footerButton = (Button) findViewById(R.id.FooterButtonHome);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(CustomerDashBoardActivity.this, ReportActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });

        footerButton = (Button) findViewById(R.id.FooterButtonInbox);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(CustomerDashBoardActivity.this, InboxActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });

        footerButton = (Button) findViewById(R.id.FooterButtonBeat);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(CustomerDashBoardActivity.this, BeatsActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });

        //receiving the Passed data from customer list
        Bundle bundle = getIntent().getExtras();
        final String message = bundle.getString("key");
         getSupportActionBar().setTitle(message);
        /*TextView shopName=(TextView)findViewById(R.id.shopName);
        shopName.setText(message);
*/

        //defining dashboard

        mSalesOrderLayout=(LinearLayout)findViewById(R.id.salesOrderLayout);
        mPaymentLayout=(LinearLayout)findViewById(R.id.paymentLayout);
        mCompetitorsLayout=(LinearLayout)findViewById(R.id.competitorsLayout);
        mPocketMISLayout=(LinearLayout)findViewById(R.id.pocketMISLayout);
        mComplaintsLayout=(LinearLayout)findViewById(R.id.complaintsLayout);
        mEnquiryLayout=(LinearLayout)findViewById(R.id.enquiryLayout);

        mSalesOrderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CustomerDashBoardActivity.this,CustomerSalesOrderActivity.class);
                intent.putExtra("key", message);
                startActivity(intent);
            }
        });

        mPaymentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CustomerDashBoardActivity.this,CustomerPaymentModeActivity.class);
                intent.putExtra("key", message);
                startActivity(intent);
            }
        });

        mPocketMISLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CustomerDashBoardActivity.this,CustomerPocketMISActivity.class);
                intent.putExtra("key", message);
                startActivity(intent);
            }
        });

        mCompetitorsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CustomerDashBoardActivity.this,CustomerCompetitorsActivity.class);
                intent.putExtra("key", message);

                startActivity(intent);
            }
        });

        mEnquiryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CustomerDashBoardActivity.this,CustomerEnquiryActivity.class);
                intent.putExtra("key", message);
                startActivity(intent);
            }
        });

        mComplaintsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CustomerDashBoardActivity.this,CustomerComplaintsActivity.class);
                intent.putExtra("key", message);
                startActivity(intent);
            }
        });





    }
}
