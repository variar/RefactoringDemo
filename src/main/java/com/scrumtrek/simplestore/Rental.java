package com.scrumtrek.simplestore;

/**
 * Single rental information
 */
public class Rental {
	private final transient Movie mMovie;
	private final transient int mDaysRented;

	public Rental(Movie movie, int daysRented) {
		mMovie = movie;
		mDaysRented = daysRented;
	}

	public int getDaysRented() {
		return mDaysRented;
	}

	public Movie getMovie() {
		return mMovie;
	}
}

