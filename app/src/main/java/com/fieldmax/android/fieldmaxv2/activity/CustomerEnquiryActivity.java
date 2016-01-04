package com.fieldmax.android.fieldmaxv2.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fieldmax.android.fieldmaxv2.R;
import com.fieldmax.android.fieldmaxv2.cards.CardViewComplaintsEnquiry;

/**
 * Created by suraj on 11/29/2015.
 */
public class CustomerEnquiryActivity extends BaseActivity {

    private Button mSubmitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("key");
        getSupportActionBar().setTitle(message);
        ViewGroup mParenLayout=(LinearLayout)findViewById(R.id.parentLayout);

        CardViewComplaintsEnquiry cardViewComplaintsEnquiry=new CardViewComplaintsEnquiry(getApplicationContext());
        mParenLayout.addView(cardViewComplaintsEnquiry);

    }
}
