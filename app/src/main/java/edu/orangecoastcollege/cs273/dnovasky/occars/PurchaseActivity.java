package edu.orangecoastcollege.cs273.dnovasky.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.DecimalFormat;

public class PurchaseActivity extends AppCompatActivity {

    private String monthlyPaymentText;
    private String loanSummaryText;

    private EditText mCarPrice;
    private EditText mDownPayment;
    private RadioButton mFourYearButton;
    private RadioButton mFiveYearButton;

    private Car mCurrentCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate our layout
        setContentView(R.layout.activity_purchase);

        // Connect member variables to our xml components
        mCarPrice = (EditText) findViewById(R.id.carPriceEditText);
        mDownPayment = (EditText) findViewById(R.id.downPaymentEditText);
        mFourYearButton = (RadioButton)findViewById(R.id.radioButton2);
        mFiveYearButton = (RadioButton)findViewById(R.id.radioButton3);

        // Initialize our car object
        mCurrentCar = new Car();
    }

    /**
     * Set our car member variables based on user input and start our new intent.
     */

    public void activateLoanReport(View view)
    {
        double price, downPayment;

        try
        {
            price = Double.parseDouble(mCarPrice.getText().toString());
            downPayment = Double.parseDouble(mDownPayment.getText().toString());
        }
        catch(NumberFormatException e)
        {
            price = 0;
            downPayment = 0;
        }

        int loanTerm = getLoanTermFromRadio();

        mCurrentCar.setPrice(price);
        mCurrentCar.setDownPayment(downPayment);
        mCurrentCar.setLoanTerm(loanTerm);

        Intent intent = myIntent();
        // Start the activity
        startActivity(intent);

    }

    /**
     * Creates our new intent and puts our extras into the intent
     * @return intent the new intent we wish to run when loan report is pressed.
     */
    private Intent myIntent() {
        Intent intent = new Intent(this, LoanSummaryActivity.class);

        constructLoanSummaryText();

        intent.putExtra("MonthlyPayment", monthlyPaymentText);
        intent.putExtra("LoanSummary", loanSummaryText);
        return intent;
    }

    /**
     * Return the loan in years based on selected radio button
     * @return loanTerm the loan in years
     */
    private int getLoanTermFromRadio() {
        int loanTerm;
        if(mFiveYearButton.isChecked())
            loanTerm = 5;
        else if(mFourYearButton.isChecked())
            loanTerm = 4;
        else
            loanTerm = 3;
        return loanTerm;
    }

    /**
     * Builds our loan summary based on all user input provided
     */
    private void constructLoanSummaryText()
    {
        DecimalFormat df = new DecimalFormat("#.00");

        monthlyPaymentText = getString(R.string.report_line1)
                + df.format(mCurrentCar.calculateMonthlyPayment());
        loanSummaryText = getString(R.string.report_line2)
                + "     " + df.format(mCurrentCar.getPrice())
                + getString(R.string.report_line3)
                + "    " + df.format(mCurrentCar.getDownPayment())
                + getString(R.string.report_line5)
                + "                " + df.format(mCurrentCar.calculateTaxAmount())
                + getString(R.string.report_line6)
                + "                  " + df.format(mCurrentCar.calculateTotalCost())
                + getString(R.string.report_line7)
                + "   " + df.format(mCurrentCar.calculateBorrowedAmount())
                + getString(R.string.report_line8)
                + "       " + df.format(mCurrentCar.calculateInterestAmount())
                + getString(R.string.report_line4)
                + " " + mCurrentCar.getLoanTerm() + " years"
                + getString(R.string.report_line9)
                + getString(R.string.report_line10)
                + getString(R.string.report_line11)
                + getString(R.string.report_line12);
    }
}
