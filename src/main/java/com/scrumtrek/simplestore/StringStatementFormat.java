package com.scrumtrek.simplestore;

import java.util.List;

/**
 * Created by omni on 20.11.15.
 */
public class StringStatementFormat implements StatementFormat {
    @Override
    public String format(Customer customer, List<RentalAmount> rentals) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Rental record for ").append(customer.getName()).append("\n");

        double totalAmount = 0;
        int frequentRenterPoints = 0;
        for(RentalAmount each: rentals) {
            totalAmount += each.getAmount();
            frequentRenterPoints += each.getFRP();

            stringBuilder.append("\t").append(each.getRental().getMovie().getTitle())
                    .append("\t").append(each.getAmount()).append("\n");
        }

        stringBuilder.append("Amount owed is ").append(totalAmount).append("\n");
        stringBuilder.append("You earned ").append(frequentRenterPoints).append(" frequent renter points.");
        return stringBuilder.toString();
    }
}
