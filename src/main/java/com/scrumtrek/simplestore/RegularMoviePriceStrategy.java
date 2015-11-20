package com.scrumtrek.simplestore;

/**
 * Created by omni on 20.11.15.
 */
public class RegularMoviePriceStrategy implements MoviePriceStrategy {
    private static final int REGULAR_RENT_THRESHOLD = 2;
    private static final double REGULAR_MULTIPLIER = 1.5;
    private static final double REGULAR_BASE_AMOUNT = 2;

    @Override
    public double getAmount(Rental rental) {
        double thisAmount = REGULAR_BASE_AMOUNT;
        if (rental.getDaysRented() > REGULAR_RENT_THRESHOLD)
        {
            thisAmount += (rental.getDaysRented() - REGULAR_RENT_THRESHOLD) * REGULAR_MULTIPLIER;
        }
        return thisAmount;
    }

    @Override
    public int getFrequentRentalPoints(Rental rental) {
        return 1;
    }
}
