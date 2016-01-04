package com.fieldmax.android.fieldmaxv2.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fieldmax.android.fieldmaxv2.R;
import com.fieldmax.android.fieldmaxv2.cards.CardViewOrderDetails;

/**
 * Created by suraj on 11/29/2015.
 */
public class CustomerPocketMISActivity extends BaseActivity {

    private Button mOrderSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_mispocket);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("key");
        getSupportActionBar().setTitle(message);
        mOrderSearchButton=(Button)findViewById(R.id.searchOrder);
        mOrderSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });

        ViewGroup mParentLayout=(LinearLayout)findViewById(R.id.parentLayout);

        CardViewOrderDetails card1=new CardViewOrderDetails(getApplicationContext());
        mParentLayout.addView(card1);

        CardViewOrderDetails card2=new CardViewOrderDetails(getApplicationContext());
        mParentLayout.addView(card2);

        CardViewOrderDetails card3=new CardViewOrderDetails(getApplicationContext());
        mParentLayout.addView(card3);

        CardViewOrderDetails card4=new CardViewOrderDetails(getApplicationContext());
        mParentLayout.addView(card4);

        CardViewOrderDetails card5=new CardViewOrderDetails(getApplicationContext());
        mParentLayout.addView(card5);

        CardViewOrderDetails card6=new CardViewOrderDetails(getApplicationContext());
        mParentLayout.addView(card6);
    }


}
