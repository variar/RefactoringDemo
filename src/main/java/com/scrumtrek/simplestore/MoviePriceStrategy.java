package com.scrumtrek.simplestore;

/**
 * Created by omni on 20.11.15.
 */
public interface MoviePriceStrategy {
    double getAmount(Rental rental);
    int getFrequentRentalPoints(Rental rental);
}
