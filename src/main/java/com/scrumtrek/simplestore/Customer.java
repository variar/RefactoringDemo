package com.scrumtrek.simplestore;

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
	private transient String m_Name;
	private transient List<Rental> m_Rentals = new ArrayList<Rental>();

	public Customer(String name) {
		m_Name = name;
	}

	public String getName() {
		return m_Name;
	}


	public void addRental(Rental arg){
		m_Rentals.add(arg);
	}

	public String Statement()
	{
		double totalAmount = 0;
		int frequentRenterPoints = 0;
				
		String result = "Rental record for " + m_Name + "\n";
		
		for(Rental each: m_Rentals) {
			double thisAmount = 0;
			
			// Determine amounts for each line
			switch(each.getMovie().getPriceCode()) {
				case Regular:
					thisAmount += RegularBaseAmount;
					if (each.getDaysRented() > RegularRentThreshold)
					{
						thisAmount += (each.getDaysRented() - RegularRentThreshold) * RegularMultiplier;
					}
					break;
	
				case NewRelease:
					thisAmount += each.getDaysRented() * NewReleaseMultiplier;
					break;
	
				case Childrens:
					thisAmount += ChildrenRentAmount;
					if (each.getDaysRented() > ChildrensRentThreshold)
					{
						thisAmount = (each.getDaysRented() - ChildrensRentThreshold) * ChlidrensMultiplier;
					}
					break;
			}

			// Add frequent renter points
			frequentRenterPoints++;

			// Add bonus for a two-day new-release rental
			if ((each.getMovie().getPriceCode() == PriceCodes.NewRelease)
					&& (each.getDaysRented() > NewReleaseMinDaysForBonus))
			{
				frequentRenterPoints++;
			}

			// Show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}

		// Add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points.";
		return result;
	}
}

