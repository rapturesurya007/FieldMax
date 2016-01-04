package com.fieldmax.android.fieldmaxv2.cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.fieldmax.android.fieldmaxv2.R;

/**
 * Created by mujeeb on 31-Dec-15.
 */
public class CardViewCustomerDashBoard extends LinearLayout {
    private Context mContext;
    public CardViewCustomerDashBoard(Context context) {
        super(context);
        this.mContext=context;
        initView();
    }
    protected void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.card_view_customer_dashboard, this, true);
    }
    }
