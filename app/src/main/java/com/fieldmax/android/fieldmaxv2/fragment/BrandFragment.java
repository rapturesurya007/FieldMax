package com.fieldmax.android.fieldmaxv2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fieldmax.android.fieldmaxv2.R;

/**
 * Created by suraj on 29/12/2015.
 */
public class BrandFragment extends Fragment {

    ListView lv;
    Context context;


    private String[] mBrandName = {"Brand 1", "Brand 2", "Brand 3 ", "Brand 4", "Brand 5",
            "Brand 6", "Brand 7", "Brand 8 ", "Brand 8", "Brand 9", "Brand 10",
            "Brand 11", "Brand 12 ", "Brand 13", "Brand 14"};

    
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.generic_list_view,container,false);
     return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv = (ListView) getActivity().findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(getActivity(), mBrandName));
        lv.setDividerHeight(10);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
