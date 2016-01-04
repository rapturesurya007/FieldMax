package com.fieldmax.android.fieldmaxv2.cards;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;

import com.fieldmax.android.fieldmaxv2.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mujeeb on 27-Dec-15.
 */
public class CardViewLineChart extends CardView {
    private Context mContext;
    private LineChart mLineChart;

    public CardViewLineChart(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    protected void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.card_view_linechart, this, true);

        mLineChart = (LineChart) view.findViewById(R.id.lineChart);
        mLineChart.setDescription("Pedigree Line");
        // enable touch gestures
        mLineChart.setTouchEnabled(true);
        // enable scaling and dragging
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        mLineChart.setPinchZoom(true);
        mLineChart.animateXY(2000, 2000);
        setData(45, 100);
    }

    private void setData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add((i) + "");
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float mult = (range + 1);
            float val = (float) (Math.random() * mult) + 3;
            yVals.add(new Entry(val, i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");

        // set the line to be drawn like this "- - - - - -"
        set1.enableDashedLine(10f, 5f, 0f);
        set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleSize(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setFillAlpha(65);
        set1.setFillColor(Color.BLACK);

        List<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1);
        // add the datasets
        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mLineChart.setData(data);
    }
}
