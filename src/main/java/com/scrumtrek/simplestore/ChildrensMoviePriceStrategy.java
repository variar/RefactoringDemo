package com.scrumtrek.simplestore;

/**
 * Created by omni on 20.11.15.
 */
public class ChildrensMoviePriceStrategy implements MoviePriceStrategy {
    public static final int CHILDRENS_RENT_THRESHOLD = 3;
    public static final double CHLIDRENS_MULTIPLIER = 1.5;
    public static final double CHILDREN_RENT_AMOUNT = 1.5;

    @Override
    public double getAmount(Rental rental) {
        double thisAmount = CHILDREN_RENT_AMOUNT;
        if (rental.getDaysRented() > CHILDRENS_RENT_THRESHOLD)
        {
            thisAmount = (rental.getDaysRented() - CHILDRENS_RENT_THRESHOLD) * CHLIDRENS_MULTIPLIER;
        }
        return thisAmount;
    }

    @Override
    public int getFrequentRentalPoints(Rental rental) {
        return 1;
    }
}
