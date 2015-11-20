package com.scrumtrek.simplestore;

/**
 * Created by omni on 20.11.15.
 */
public class RentalAmount {
    Rental mRental;
    double mAmount;
    double mFRP;

    public RentalAmount(Rental rental, double amount, double FRP) {
        this.mRental = rental;
        this.mAmount = amount;
        this.mFRP = FRP;
    }

    public Rental getRental() {
        return mRental;
    }

    public double getAmount() {
        return mAmount;
    }

    public double getFRP() {
        return mFRP;
    }
}
