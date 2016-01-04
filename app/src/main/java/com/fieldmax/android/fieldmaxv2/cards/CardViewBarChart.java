package com.fieldmax.android.fieldmaxv2.cards;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;

import com.fieldmax.android.fieldmaxv2.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by mujeeb on 25-Dec-15.
 */
public class CardViewBarChart extends CardView {
    private BarChart mBarchart;
    private Context mContext;
    public CardViewBarChart(Context context) {
        super(context);
       this.mContext=context;
        initView();
    }

    protected void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.card_view_barchart, this, true);

        //Bar chart implementation
        mBarchart = (BarChart) view.findViewById(R.id.barChart);
        BarData data = new BarData(getXAxisValues(), getDataSet());
        mBarchart.setData(data);
        mBarchart.setDescription("Pedigree");
        mBarchart.animateXY(2000, 2000);
        mBarchart.invalidate();
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);

        ArrayList<BarEntry> valueSet3 = new ArrayList<>();
        BarEntry v3e1 = new BarEntry(100.000f, 0); // Jan
        valueSet3.add(v3e1);
        BarEntry v3e2 = new BarEntry(60.000f, 1); // Feb
        valueSet3.add(v3e2);
        BarEntry v3e3 = new BarEntry(80.000f, 2); // Mar
        valueSet3.add(v3e3);
        BarEntry v3e4 = new BarEntry(40.000f, 3); // Apr
        valueSet3.add(v3e4);
        BarEntry v3e5 = new BarEntry(40.000f, 4); // May
        valueSet3.add(v3e5);
        BarEntry v3e6 = new BarEntry(100.000f, 5); // Jun
        valueSet3.add(v3e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "Brand 3");
        barDataSet3.setColor(Color.rgb(0, 200, 1));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        return xAxis;
    }
    }
