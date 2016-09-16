package edu.orangecoastcollege.cs273.dnovasky.occars;

/**
 * Created by dnova_000 on 9/13/2016.
 * This class represents a car you wish to purchase. It contains
 * the price, your down payment, your loan term, and the current
 * tax rate.
 * @author Donald E Novasky
 */
public class Car {

    // The current tax rate
    private static final double TAX_RATE = 8.0;

    // Amount buyer wishes to put down
    private double mDownPayment;
    // Desired loan term in years.
    private int mLoanTerm;
    // Cars sticker price
    private double mPrice;

    /**
     * Gets the cars sticker price.
     * @return mPrice the sticker price
     */
    public double getPrice() {
        return mPrice;
    }

    /**
     * Calculates the amount you'll need to borrow.
     * @return the amount you'll need to borrow
     */
    public double calculateBorrowedAmount()
    {
        return Math.round(mPrice + calculateTaxAmount() - mDownPayment);
    }

    /**
     * Calculates the interest amount based on current A.P.R.
     * @return the amount of interest
     */
    public double calculateInterestAmount()
    {
        double borrowed = calculateBorrowedAmount();
        double rate;
        if(mLoanTerm == 3)
            rate = 4.62;
        else if(mLoanTerm == 4)
            rate = 4.16;
        else rate = 4.19;

        return borrowed * (rate / 100);
    }

    /**
     * Calculates the monthly payments based on loan term.
     * @return monthly payment
     */
    public double calculateMonthlyPayment()
    {
        return (calculateBorrowedAmount()+ calculateInterestAmount())
                / (mLoanTerm * 12);
    }

    /**
     * Calculates the amount of tax you'll pay based on current tax rate.
     * @return the total tax amount
     */
    public double calculateTaxAmount()
    {
        return mPrice * (TAX_RATE / 100);
    }

    /**
     * Calculates the total cost of the vehicle not including interest.
     * @return the total cost of the vehicle
     */
    public double calculateTotalCost()
    {
        return mPrice + calculateTaxAmount();
    }

    /**
     * Sets the purchase price of the vehicle.
     * @param price the purchase price
     */
    public void setPrice(double price) {
        mPrice = price;
    }

    /**
     * Gets the loan term of the vehicle in years.
     * @return the loan term in years
     */
    public int getLoanTerm() {
        return mLoanTerm;
    }

    /**
     * Sets the loan term in years
     * @param loanTerm the loan term in years
     */
    public void setLoanTerm(int loanTerm) {
        mLoanTerm = loanTerm;
    }

    /**
     * Gets the down payment made on the vehicle.
     * @return the downpayment
     */
    public double getDownPayment() {
        return mDownPayment;
    }

    /**
     * Sets the desired downpayment.
     * @param downPayment the desired downpayment
     */
    public void setDownPayment(double downPayment) {
        mDownPayment = downPayment;
    }
}
