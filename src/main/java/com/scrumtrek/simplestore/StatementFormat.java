package com.scrumtrek.simplestore;

import java.util.List;

/**
 * Created by omni on 20.11.15.
 */
public interface StatementFormat {
    String format(Customer customer, List<RentalAmount> rentals);
}
