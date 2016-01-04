package com.fieldmax.android.fieldmaxv2.cards;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;

import com.fieldmax.android.fieldmaxv2.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by mujeeb on 26-Dec-15.
 */
public class CardViewPieChart extends CardView {
    private Context mContext;
    private PieChart mPieChart;
    public CardViewPieChart(Context context) {
        super(context);
        this.mContext=context;
        initView();
    }
    protected void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.card_view_piechart, this, true);

        //Pie Chart implementation
        mPieChart = (PieChart)view.findViewById(R.id.pieChart);
        mPieChart.setUsePercentValues(true);
        mPieChart.setDescription("Pedigree Pie");
        // enable hole and configure
        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColorTransparent(true);
        mPieChart.setHoleRadius(7);
        mPieChart.setTransparentCircleRadius(10);
        mPieChart.animateXY(2000, 2000);
        //add data
        addData();
    }
    private void addData() {
        float[] yData = {5, 10, 15, 30, 40, 50};
        String[] xData = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN"};

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i = 0; i < yData.length; i++)
            yVals1.add(new Entry(yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        PieDataSet dataSet = new PieDataSet(yVals1, "Brands");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);

        mPieChart.setData(data);
        mPieChart.highlightValues(null);
        mPieChart.invalidate();
    }


}
