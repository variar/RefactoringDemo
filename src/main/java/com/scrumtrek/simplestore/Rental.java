package com.scrumtrek.simplestore;

public class Rental {
	private transient Movie mMovie;
	private transient int mDaysRented;

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

