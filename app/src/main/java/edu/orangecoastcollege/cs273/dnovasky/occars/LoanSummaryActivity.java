package edu.orangecoastcollege.cs273.dnovasky.occars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoanSummaryActivity extends Activity {

    private TextView mMonthlyPayment;
    private TextView mLoanSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate our view
        setContentView(R.layout.activity_loan_summary);

        // Connect or member variables to our xml components
        mMonthlyPayment = (TextView) findViewById(R.id.monthlyPaymentTextView);
        mLoanSummary = (TextView) findViewById(R.id.loanReportTextView);

        // Get our extras from the intent and set our TextViews to display monthly
        // payment and loan summary.
        Intent intent = getIntent();
        mMonthlyPayment.setText(intent.getStringExtra("MonthlyPayment"));
        mLoanSummary.setText(intent.getStringExtra("LoanSummary"));
    }

    // On button click end the current activity and return to PurchaseActivity
    public void returnToDataEntry(View view)
    {
        finish();
    }
}
