package com.scrumtrek.simplestore;

public class Movie {
	private transient String mTitle;
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

