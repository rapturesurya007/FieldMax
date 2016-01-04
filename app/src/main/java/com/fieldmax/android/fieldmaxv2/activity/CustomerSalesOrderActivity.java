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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fieldmax.android.fieldmaxv2.R;

/**
 * Created by suraj on 11/29/2015.
 */
public class CustomerSalesOrderActivity extends BaseActivity {
    private EditText mEditProduct;
    private String[] mBrandName = {"Brand 1", "Brand 2", "Brand 3 ", "Brand 4", "Brand 5",
                                   "Brand 6", "Brand 7", "Brand 8 ", "Brand 8", "Brand 9", "Brand 10",
                                          "Brand 11", "Brand 12 ", "Brand 13", "Brand 14"};
    ListView lv;
    Context context;
    private Button mSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_salesorder);
       // mSearchButton=(Button)findViewById(R.id.searchButton);
        Bundle bundle = getIntent().getExtras();
        final String message = bundle.getString("key");
        getSupportActionBar().setTitle(message);
      /*  mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
*/
        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, mBrandName));

        //passing the item to the next activity onclick of listview item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), MyExpandableListView.class);
               // intent.putExtra("key1", message);
               // intent.putExtra("key", mBrandName[i]);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }

    public class CustomAdapter extends BaseAdapter {

        String[] brandName;
        Context mContext;

        private LayoutInflater inflater = null;

        public CustomAdapter(Context context, String[] mBrandName) {
            brandName = mBrandName;
            mContext = context;


            inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return brandName.length;
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
            rowView = inflater.inflate(R.layout.layout_brands, null);
            holder.mBrandName = (TextView) rowView.findViewById(R.id.brandName);
            holder.mBrandName.setText(brandName[position]);
            return rowView;
        }

        public class Holder {
            TextView mBrandName;


        }


    }
}
