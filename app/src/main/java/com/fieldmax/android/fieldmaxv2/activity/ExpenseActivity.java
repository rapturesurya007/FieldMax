package com.fieldmax.android.fieldmaxv2.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.fieldmax.android.fieldmaxv2.R;
import com.fieldmax.android.fieldmaxv2.maps.BeatsActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by suraj on 11/2/2015.
 */
public class ExpenseActivity extends BaseActivity {

    private TextView mTotalExpense;
    private EditText mExpenseDescription, mExpenseDate, mFoodExpense, mPetrolExpense, mOtherExpense, mMobileExpense;
    private int foodExpense, petrolExpense, otherExpense, mobileExpense, totalExpense;
    private Button mGetTotalExpense;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
       /* Button footerCustomerButton = (Button) findViewById(R.id.FooterButtonExpense);
        footerCustomerButton.setSelected(true);
*/
        Button footerButton = (Button) findViewById(R.id.FooterButtonCustomer);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(ExpenseActivity.this, CustomerListActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });


        footerButton = (Button) findViewById(R.id.FooterButtonInbox);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(ExpenseActivity.this, InboxActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });

        footerButton = (Button) findViewById(R.id.FooterButtonBeat);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(ExpenseActivity.this, BeatsActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });

        mExpenseDate = (EditText) findViewById(R.id.expenseDate);
        mFoodExpense = (EditText) findViewById(R.id.foodExpense);
        mPetrolExpense = (EditText) findViewById(R.id.petrolExpense);
        mMobileExpense = (EditText) findViewById(R.id.mobileExpense);
        mOtherExpense = (EditText) findViewById(R.id.otherExpense);
        mTotalExpense = (TextView) findViewById(R.id.totalExpense);
        mExpenseDescription = (EditText) findViewById(R.id.expenseDescription);

        //setting default date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        mExpenseDate.setText(sdf.format(new Date()));


        //Implementing ExpenseDate
        mExpenseDate.setInputType(InputType.TYPE_NULL);
        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        mExpenseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ExpenseActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //showing the description box for other expenses

        mOtherExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExpenseDescription.setVisibility(View.VISIBLE);
            }
        });

        //getting all Data and calculating the total expense


        mGetTotalExpense = (Button) findViewById(R.id.getTotalExpense);
        mGetTotalExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    petrolExpense = Integer.parseInt(mPetrolExpense.getText().toString());
                    foodExpense = Integer.parseInt(mFoodExpense.getText().toString());
                    mobileExpense = Integer.parseInt(mMobileExpense.getText().toString());
                    otherExpense = Integer.parseInt(mOtherExpense.getText().toString());
                } catch (NumberFormatException e) {
                    // handle the exception
                }

                totalExpense = petrolExpense + foodExpense + mobileExpense + otherExpense;
                mTotalExpense.setText(Integer.toString(totalExpense));
               /* mFoodExpense.setText("");
                mMobileExpense.setText("");
                mOtherExpense.setText("");
                mPetrolExpense.setText("");
                mExpenseDate.setText("");
*/
                 //Also  here implement the Web Service to send the whole data into database

            }
        });


    }

    private void updateLabel() {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        mExpenseDate.setText(sdf.format(myCalendar.getTime()));


    }


}
