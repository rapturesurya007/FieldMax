package com.fieldmax.android.fieldmaxv2.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fieldmax.android.fieldmaxv2.R;

/**
 * Created by suraj on 12/8/2015.
 */
public class CustomerPaymentModeActivity extends BaseActivity {
    private RadioGroup mPaymentMode;
    private RadioButton mCashMode, mChequeMode, mDemandDraftMode;
    private LinearLayout mCashModeLayout, mChequeModeLayout, mDemandDraftModeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_payment_mode);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("key");
        getSupportActionBar().setTitle(message);
      //  getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_left_carat_selected);
        /*mPaymentMode = (RadioGroup) findViewById(R.id.paymentMode);
        mCashModeLayout = (LinearLayout) findViewById(R.id.cashModeLayout);
        mChequeModeLayout = (LinearLayout) findViewById(R.id.chequeModeLayout);
        mDemandDraftModeLayout = (LinearLayout) findViewById(R.id.demandDraftModeLayout);
        mCashMode = (RadioButton) findViewById(R.id.cashMode);
        mCashMode = (RadioButton) findViewById(R.id.chequeMode);
        mDemandDraftMode = (RadioButton) findViewById(R.id.demandDraftMode);


        mPaymentMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.cashMode) {
                    mCashModeLayout.setVisibility(View.VISIBLE);
                    mChequeModeLayout.setVisibility(View.GONE);
                    mDemandDraftModeLayout.setVisibility(View.GONE);


                } else if (i == R.id.chequeMode) {
                    mChequeModeLayout.setVisibility(View.VISIBLE);
                    mCashModeLayout.setVisibility(View.GONE);
                    mDemandDraftModeLayout.setVisibility(View.GONE);

                }
                else if (i == R.id.demandDraftMode) {
                    mDemandDraftModeLayout.setVisibility(View.VISIBLE);
                    mChequeModeLayout.setVisibility(View.GONE);
                    mCashModeLayout.setVisibility(View.GONE);

                }
            }
        });*/
    }
}
