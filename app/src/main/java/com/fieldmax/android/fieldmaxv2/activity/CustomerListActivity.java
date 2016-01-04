package com.fieldmax.android.fieldmaxv2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.fieldmax.android.fieldmaxv2.R;
import com.fieldmax.android.fieldmaxv2.maps.BeatsActivity;

/**
 * Created by suraj on 11/3/2015.
 */
public class CustomerListActivity extends BaseActivity {
    ListView lv;
    Context context;
    CustomAdapter myAdapter;

    int pos;
    private String[] mCustomerName = {"SHARMA TRADERS", "CHOUDHARY TRADERS", "RAM TRADERS ", "GOPAL SHOP",
            "NANDINI", "SHARMA TRADERS", "CHOUDHARY TRADERS", "RAM TRADERS ", "GOPAL SHOP", "NANDINI",
            "SHARMA TRADERS", "CHOUDHARY TRADERS", "RAM TRADERS ", "GOPAL SHOP", "NANDINI"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_customer);
        Button footerCustomerButton = (Button) findViewById(R.id.FooterButtonCustomer);
        footerCustomerButton.setSelected(true);

        Button footerInboxButton = (Button) findViewById(R.id.FooterButtonInbox);
        footerInboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(CustomerListActivity.this, InboxActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });


        Button footerHomeButton = (Button) findViewById(R.id.FooterButtonHome);
        footerHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(CustomerListActivity.this, ReportActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });

        Button footerBeatButton = (Button) findViewById(R.id.FooterButtonBeat);
        footerBeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(CustomerListActivity.this, BeatsActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });

        context = this;

        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, mCustomerName));

        //passing the item to the next activity onclick of listview item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), CustomerDashBoardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("key", mCustomerName[i]);
                startActivity(intent);
            }
        });


    }

    public class CustomAdapter extends BaseAdapter {

        String[] customerName;
        Context mContext;

        private LayoutInflater inflater = null;

        public CustomAdapter(Context context, String[] mCustomerName) {
            customerName = mCustomerName;
            mContext = context;

            inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return customerName.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final Holder holder = new Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.layout_customers, null);
            holder.mCustomerName = (TextView) rowView.findViewById(R.id.customerName);
            holder.mCustomerName.setText(mCustomerName[position]);
            return rowView;
        }

        public class Holder {
            TextView mCustomerName;

        }


    }



}