package com.fieldmax.android.fieldmaxv2.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.fieldmax.android.fieldmaxv2.R;

/**
 * Created by suraj on 12/8/2015.
 */
public class CustomerSalesOrderBrandProductsActivity extends BaseActivity {
    ListView lv;
    Context context;
    String getSKU;
    private Spinner mSpinnerSKU;
    private TextView mBrandProductSKU;
    private LayoutInflater inflater = null;

    private String[] mBrandProductName = {"Product 1", "Product 2", "Product 3 ", "Product 4",
            "Product 5", "Product 6", "Product 7", "Product 8 ", "Product 9", "Product 10"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_brand_product);
        mSpinnerSKU=(Spinner)findViewById(R.id.spinnerSKU);
        mBrandProductSKU=(TextView)findViewById(R.id.brandSKU);
        Bundle bundle1 = getIntent().getExtras();
        final String message1 = bundle1.getString("key1");
        getSupportActionBar().setTitle(message1);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("key");
        TextView shopName=(TextView)findViewById(R.id.brandName);
        shopName.setText(message);
        context = this;




        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, mBrandProductName));
    }



            public class CustomAdapter extends BaseAdapter {

        String[] brandProductName;
        String[] brandQuantity;
        Context mContext;


        public CustomAdapter(Context context, String[] mBrandProductName) {
            brandProductName = mBrandProductName;
            mContext = context;

            inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return brandProductName.length;
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

            convertView = inflater.inflate(R.layout.layout_products, null);
            holder.mBrandProductName = (TextView) convertView.findViewById(R.id.brandsProductName);
            holder.mBrandProductQuantity=(EditText)convertView.findViewById(R.id.productQuantity);
            final TextView mBrandProductSKU=(TextView)convertView.findViewById(R.id.brandSKU);
            Spinner mSpinnerSKU=(Spinner)convertView.findViewById(R.id.spinnerSKU);
            holder.mBrandProductName.setText(mBrandProductName[position]);
            String[] SKU={"500gm","1KG","2KG"};

            ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,SKU);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinnerSKU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    getSKU=adapterView.getItemAtPosition(i).toString();

                    mBrandProductSKU.setText(getSKU);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            mSpinnerSKU.setAdapter(adapter);

            return convertView;
        }

        public class Holder {
            TextView mBrandProductName;
            EditText mBrandProductQuantity;
            TextView mBrandProductSKU;
            Spinner mSpinnerSKU;


        }


    }
}
