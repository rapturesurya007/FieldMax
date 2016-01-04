package com.fieldmax.android.fieldmaxv2.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fieldmax.android.fieldmaxv2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by suraj on 27/12/2015.
 */
public class MyExpandableListView extends BaseActivity {

    ExpandableListView myExpandableListView;
    Context mContext;
    List<String> listDataHeader;
    List<Integer> listDataHeaderImage;
    HashMap<String, List<String>> listDataChild;


    private String[] mChildProductSKU = {"0.5KG", "1KG", "2KG ", "5KG","10KG","20KG","50KG"};
    private String[] mChildProductMRP = {"Rs100", "Rs200", "Rs300 ", "Rs400","Rs500","Rs600","Rs700"};
    private String[] mChildProductRetailerMRP = {"Rs90", "Rs190", "Rs290 ", "Rs390","Rs490","Rs590","Rs690"};
    int [] mParentProductImage={R.drawable.image_holder,R.drawable.image_holder,R.drawable.image_holder,R.drawable.image_holder,R.drawable.image_holder};



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_expandable_list_view);
        myExpandableListView =(ExpandableListView)findViewById(R.id.expandableListView);
        prepareListData();
        mContext=this;
        myExpandableListView.setAdapter(new CustomListAdapter(this,mParentProductImage,listDataHeader,listDataChild,mChildProductSKU,mChildProductMRP,mChildProductRetailerMRP));

        myExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
        {
            @Override
            public void onGroupExpand(int groupPosition)
            {

            }
        });

        myExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener()
        {
            @Override
            public void onGroupCollapse(int groupPosition)
            {

            }
        });

        // Listview on child click listener
        myExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });


    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataHeaderImage=new ArrayList<Integer>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Product1");
        listDataHeader.add("Product2");
        listDataHeader.add("Product3");
        listDataHeader.add("Product4");
        listDataHeader.add("Product5");


        // Adding child data
        ArrayList<String> Product1 = new ArrayList<String>();
        Product1.add("Product1");
        Product1.add("Product1");
        Product1.add("Product1");
        Product1.add("Product1");
        Product1.add("Product1");
        Product1.add("Product1");
        Product1.add("Product1");


        ArrayList<String> Product2 = new ArrayList<String>();
        Product2.add("Product2");
        Product2.add("Product2");
        Product2.add("Product2");
        Product2.add("Product2");
        Product2.add("Product2");
        Product2.add("Product2");
        Product2.add("Product2");

        ArrayList<String> Product3 = new ArrayList<String>();
        Product3.add("Product3");
        Product3.add("Product3");
        Product3.add("Product3");
        Product3.add("Product3");
        Product3.add("Product3");
        Product3.add("Product3");
        Product3.add("Product3");

        ArrayList<String> Product4 = new ArrayList<String>();
        Product4.add("Product4");
        Product4.add("Product4");
        Product4.add("Product4");
        Product4.add("Product4");
        Product4.add("Product4");
        Product4.add("Product4");
        Product4.add("Product4");

        ArrayList<String>Product5 = new ArrayList<String>();
        Product5.add("Product5");
        Product5.add("Product5");
        Product5.add("Product5");
        Product5.add("Product5");
        Product5.add("Product5");
        Product5.add("Product5");
        Product5.add("Product5");

        listDataChild.put(listDataHeader.get(0), Product1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Product2);
        listDataChild.put(listDataHeader.get(2), Product3);
        listDataChild.put(listDataHeader.get(3), Product4);
        listDataChild.put(listDataHeader.get(4), Product5);
    }

    public class CustomListAdapter extends BaseExpandableListAdapter{

        private Context _context;
        private List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, List<String>> _listDataChild;
        String[] childProductSKU;
        String [] childProductMRP;
        String[] childProductRetailerMRP;
        int[] parentProductImage;


        public CustomListAdapter(Context context, int [] mParentProductImage,List<String> listDataHeader,
                                     HashMap<String, List<String>> listChildData,String[] mChildProductSKU,
                                     String [] mChildProductMRP,String[] mChildProductRetailerMRP)
        {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
             this.childProductSKU=mChildProductSKU;
            childProductMRP=mChildProductMRP;
            childProductRetailerMRP=mChildProductRetailerMRP;
            parentProductImage=mParentProductImage;

        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.layout_expandable_parent_list_item, null);
            }
            ImageView parentProductImageView=(ImageView)convertView.findViewById(R.id.parentProductImageView);
            TextView parentProduct=(TextView)convertView.findViewById(R.id.parentProductName);
                parentProduct.setText(headerTitle);
            parentProductImageView.setImageResource(parentProductImage[groupPosition]);
            return convertView;
        }


        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }


        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .get(childPosition);
        }


        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }




        @Override
        public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup parent) {
            String child = (String) getChild(groupPosition, childPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.layout_expandable_child_list_item, null);

            }
            TextView childProduct=(TextView)convertView.findViewById(R.id.childProductName);
            childProduct.setText(child);
            TextView brandSKU=(TextView)convertView.findViewById(R.id.brandSKU);
            TextView brandMRP=(TextView)convertView.findViewById(R.id.brandMRP);
            TextView brandRetailerPrice=(TextView)convertView.findViewById(R.id.brandRetailerPrice);

            brandSKU.setText(mChildProductSKU[childPosition]);
            brandMRP.setText(mChildProductMRP[childPosition]);
            brandRetailerPrice.setText(mChildProductRetailerMRP[childPosition]);


            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}