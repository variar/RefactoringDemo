package com.scrumtrek.simplestore;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	public static final int RegularRentThreshold = 2;
	public static final int ChildrensRentThreshold = 3;
	public static final double RegularMultiplier = 1.5;
	public static final int NewReleaseMultiplier = 3;
	public static final double ChlidrensMultiplier = 1.5;
	public static final int RegularBaseAmount = 2;
	public static final double ChildrenRentAmount = 1.5;
	public static final int NewReleaseMinDaysForBonus = 1;
	public static final int BaseFrequentRenterPoints = 1;

	private transient String mName;
	private transient List<Rental> mRentals = new ArrayList<Rental>();

	public Customer(String name) {
		mName = name;
	}

	public String getName() {
		return mName;
	}


	public void addRental(Rental arg){
		mRentals.add(arg);
	}

	public String Statement()
	{
		double totalAmount = 0;
		int frequentRenterPoints = 0;
				
		String result = "Rental record for " + mName + "\n";
		
		for(Rental each: mRentals) {
			double thisAmount = 0;

			thisAmount += getAmountForRent(each);
			frequentRenterPoints += getFrequentRenterPoints(each);

			result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}

		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points.";
		return result;
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
		int frequentRenterPoints = BaseFrequentRenterPoints;

		if ((rental.getMovie().getPriceCode() == PriceCodes.NewRelease)
                && (rental.getDaysRented() > NewReleaseMinDaysForBonus))
        {
            frequentRenterPoints++;
        }
		return frequentRenterPoints;
	}

	private double getAmountForChildrensRelease(Rental rental) {
		double thisAmount = ChildrenRentAmount;
		if (rental.getDaysRented() > ChildrensRentThreshold)
        {
            thisAmount = (rental.getDaysRented() - ChildrensRentThreshold) * ChlidrensMultiplier;
        }
		return thisAmount;
	}

	private double getAmountForNewRelease(Rental rental) {
		return rental.getDaysRented() * NewReleaseMultiplier;
	}

	private double getAmountForRegularMovie(Rental rental) {
		double thisAmount = RegularBaseAmount;
		if (rental.getDaysRented() > RegularRentThreshold)
        {
            thisAmount += (rental.getDaysRented() - RegularRentThreshold) * RegularMultiplier;
        }
		return thisAmount;
	}
}

