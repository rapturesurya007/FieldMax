package com.fieldmax.android.fieldmaxv2.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.fieldmax.android.fieldmaxv2.R;

/**
 * Created by suraj on 29/12/2015.
 */
public class MultiLevelExpandableListView extends BaseActivity {

    String[] Brands={"ADIDAS","NIKE","PUMA","REBOOK","JORDAN"};
    String[] BrandProducts={"SHOES","SHOCKS","TSHIRTS","LOWER","SHORTS"};



    public static final int THIRD_LEVEL_COUNT = 20;
    private ExpandableListView expandableListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_expandable_list_view);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setAdapter(new ParentLevel(this,Brands,BrandProducts));
    }

    public class ParentLevel extends BaseExpandableListAdapter {
       private String[] brands;
        private String[] brandProducts;
        private Context context;

        public ParentLevel(Context context,String[] mBrands,String[] mBrandProducts) {
            this.context = context;
            brands=mBrands;
            brandProducts=mBrandProducts;
        }

        @Override
        public Object getChild(int arg0, int arg1) {
            return arg1;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(MultiLevelExpandableListView.this);
            secondLevelELV.setAdapter(new SecondLevelAdapter(context,brandProducts));
            secondLevelELV.setGroupIndicator(null);
            return secondLevelELV;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
                return brandProducts.length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {
            return brands.length;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.layout_brands, null);
                TextView text = (TextView) convertView.findViewById(R.id.brandName);
                text.setText(brands[groupPosition]);
            }
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    public class SecondLevelExpandableListView extends ExpandableListView {

        public SecondLevelExpandableListView(Context context) {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }



    public class SecondLevelAdapter extends BaseExpandableListAdapter {

        private Context context;
        private String[] brandProducts;
        private String[] brandProductsSubProducts;


        public SecondLevelAdapter(Context context,String[] mBrandProducts) {
            this.context = context;
            brandProducts=mBrandProducts;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {
            return brandProducts.length;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.layout_expandable_parent_list_item, null);
                TextView text = (TextView) convertView.findViewById(R.id.parentProductName);
                text.setText(brandProducts[groupPosition]);
            }
            return convertView;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.layout_products, null);
                TextView text = (TextView) convertView.findViewById(R.id.brandsProductName);
                text.setText("THIRD LEVEL");
            }
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return THIRD_LEVEL_COUNT;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}