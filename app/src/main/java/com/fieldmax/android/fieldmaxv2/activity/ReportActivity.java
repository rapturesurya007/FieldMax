package com.fieldmax.android.fieldmaxv2.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fieldmax.android.fieldmaxv2.R;
import com.fieldmax.android.fieldmaxv2.cards.CardViewBarChart;
import com.fieldmax.android.fieldmaxv2.cards.CardViewLineChart;
import com.fieldmax.android.fieldmaxv2.cards.CardViewPieChart;
import com.fieldmax.android.fieldmaxv2.maps.BeatsActivity;


/**
 * Created by suraj on 11/25/2015.
 */
public class ReportActivity extends BaseActivity {

    private ViewGroup mParentlayout;
    private Context mContext;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        mContext = this;
        activity=this;
        mParentlayout = (LinearLayout) findViewById(R.id.chart);

        CardViewBarChart mBarChart = new CardViewBarChart(mContext);
        mParentlayout.addView(mBarChart);

        CardViewPieChart mPieChart = new CardViewPieChart(mContext);
        mParentlayout.addView(mPieChart);

        CardViewLineChart mLineChart = new CardViewLineChart(mContext);
        mParentlayout.addView(mLineChart);

        CardViewBarChart mBarChart1 = new CardViewBarChart(mContext);
        mParentlayout.addView(mBarChart1);
        Button footerButton = (Button) findViewById(R.id.FooterButtonHome);
        footerButton.setSelected(true);
        footerButton = (Button) findViewById(R.id.FooterButtonCustomer);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(ReportActivity.this, CustomerListActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });


        footerButton = (Button) findViewById(R.id.FooterButtonInbox);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(ReportActivity.this, InboxActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });



        footerButton = (Button) findViewById(R.id.FooterButtonBeat);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(ReportActivity.this, BeatsActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });


    }

}
