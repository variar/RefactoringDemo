package com.scrumtrek.simplestore;

/**
 * This class holds movie info
 */
public class Movie {
	private final transient String mTitle;
	private transient PriceCodes mPriceCode;

	public Movie(String title, PriceCodes priceCode) {
		mTitle = title;
		mPriceCode = priceCode;
	}

	public PriceCodes getPriceCode()	{
		return mPriceCode;
	}
	
	public void setPriceCode(PriceCodes value) {
		mPriceCode = value;
	}

	public String getTitle() {
		return mTitle;
	}
}

