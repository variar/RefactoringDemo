package com.scrumtrek.simplestore;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds rental for single customer
 */
public class Customer {

	private transient String mName;
	private transient List<Rental> mRentals = new ArrayList<Rental>();

	public Customer(String name) {
		mName = name;
	}

	public String getName() {
		return mName;
	}

	public void addRental(Rental arg) {
		mRentals.add(arg);
	}

	public List<Rental> getRentals() {
		return mRentals;
	}
}

