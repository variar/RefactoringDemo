package com.scrumtrek.simplestore;

/**
 * Created by omni on 20.11.15.
 */
public class NewReleasePriceStrategy implements MoviePriceStrategy {
    public static final double NEW_RELEASE_MULTIPLIER = 3;
    public static final int NEW_RELEASE_MIN_DAYS_FOR_BONUS = 1;

    @Override
    public double getAmount(Rental rental) {
        return rental.getDaysRented() * NEW_RELEASE_MULTIPLIER;
    }

    @Override
    public int getFrequentRentalPoints(Rental rental) {
        int frequentRenterPoints = 1;
        if (rental.getDaysRented() > NEW_RELEASE_MIN_DAYS_FOR_BONUS)
        {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}
