package com.scrumtrek.simplestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by omni on 20.11.15.
 */
public class Statement {
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
        return getPriceStrategy(rental).getAmount(rental);
    }

    private int getFrequentRenterPoints(Rental rental) {
        return getPriceStrategy(rental).getFrequentRentalPoints(rental);
    }

    private MoviePriceStrategy getPriceStrategy(Rental rental) {
        switch(rental.getMovie().getPriceCode()) {
            case Regular:
                return new RegularMoviePriceStrategy();
            case NewRelease:
                return new NewReleasePriceStrategy();
            case Childrens:
                return new ChildrensMoviePriceStrategy();
            default:
                throw new IllegalArgumentException();
        }
    }
}
