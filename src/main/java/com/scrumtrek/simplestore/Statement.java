package com.scrumtrek.simplestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by omni on 20.11.15.
 */
public class Statement {
    public static final int REGULAR_RENT_THRESHOLD = 2;
    public static final int CHILDRENS_RENT_THRESHOLD = 3;

    public static final double REGULAR_MULTIPLIER = 1.5;
    public static final double NEW_RELEASE_MULTIPLIER = 3;
    public static final double CHLIDRENS_MULTIPLIER = 1.5;

    public static final double REGULAR_BASE_AMOUNT = 2;
    public static final double CHILDREN_RENT_AMOUNT = 1.5;

    public static final int NEW_RELEASE_MIN_DAYS_FOR_BONUS = 1;
    public static final int BASE_FREQUENT_RENTER_POINTS = 1;

    /**
     * Calculate amount for all rents
     * @return string with statement for customer
     */
    public String generateStatement(Customer customer, StatementFormat format)
    {
        List<RentalAmount> results = calculateAmount(customer.getRentals());
        return format.format(customer, results);
    }

    private  List<RentalAmount> calculateAmount(List<Rental> rentals)
    {
        List<RentalAmount> results = new ArrayList<>();
        for(Rental each: rentals) {
            double rentalAmount = getAmountForRent(each);
            int frequentRenterPoints = getFrequentRenterPoints(each);
            results.add(new RentalAmount(each, rentalAmount, frequentRenterPoints));
        }
        return results;
    }

    private double getAmountForRent(Rental rental) {
        switch(rental.getMovie().getPriceCode()) {
            case Regular:
                return getAmountForRegularMovie(rental);
            case NewRelease:
                return getAmountForNewRelease(rental);

            case Childrens:
                return getAmountForChildrensRelease(rental);

            default:
                throw new IllegalArgumentException();
        }
    }

    private int getFrequentRenterPoints(Rental rental) {
        int frequentRenterPoints = BASE_FREQUENT_RENTER_POINTS;

        if ((rental.getMovie().getPriceCode() == PriceCodes.NewRelease)
                && (rental.getDaysRented() > NEW_RELEASE_MIN_DAYS_FOR_BONUS))
        {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    private double getAmountForChildrensRelease(Rental rental) {
        double thisAmount = CHILDREN_RENT_AMOUNT;
        if (rental.getDaysRented() > CHILDRENS_RENT_THRESHOLD)
        {
            thisAmount = (rental.getDaysRented() - CHILDRENS_RENT_THRESHOLD) * CHLIDRENS_MULTIPLIER;
        }
        return thisAmount;
    }

    private double getAmountForNewRelease(Rental rental) {
        return rental.getDaysRented() * NEW_RELEASE_MULTIPLIER;
    }

    private double getAmountForRegularMovie(Rental rental) {
        double thisAmount = REGULAR_BASE_AMOUNT;
        if (rental.getDaysRented() > REGULAR_RENT_THRESHOLD)
        {
            thisAmount += (rental.getDaysRented() - REGULAR_RENT_THRESHOLD) * REGULAR_MULTIPLIER;
        }
        return thisAmount;
    }
}
