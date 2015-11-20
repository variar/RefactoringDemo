import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import com.scrumtrek.simplestore.Rental;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer mCustomer;

    private class RentalBuilder {
        private String mmovieName = "test movie";
        private PriceCodes mpriceCode = PriceCodes.Regular;
        private int mdays = 1;

        public RentalBuilder SetMovieName(String name) {
            mmovieName = name;
            return this;
        }

        public RentalBuilder SetPriceCode(PriceCodes code) {
            mpriceCode = code;
            return this;
        }

        public RentalBuilder SetRentalDays(int days) {
            mdays = days;
            return this;
        }

        public Rental Build() {
            return new Rental(new Movie(mmovieName, mpriceCode), mdays);
        }
    }

    @Before
    public void createCustomer() {
        mCustomer = new Customer("name");
    }

    @Test
    public void shouldReturnNameWhenGetName() {
        assertEquals("name", mCustomer.getName());
    }

    @Test
    public void shouldNotCrashWhenCallStatement() {
        try {
            mCustomer.Statement();
        }
        catch (Exception err) {
            fail(err.getMessage());
        }
    }

    @Test
    public void shouldNotCrashWhenAddRental() {
        Rental rental = org.mockito.Mockito.mock(Rental.class);
        try {
            mCustomer.addRental(rental);
        }
        catch (Exception err) {
            fail(err.getMessage());
        }
    }

    @Test
    public void shouldState0AmountWhenCallStatementWithoutRentals() {
        String statement = mCustomer.Statement();
        assertTrue(statement.contains("Amount owed is 0"));
    }

    @Test
    public void shouldState0FRPWhenCallStatementWithoutRentals() {
        String statement = mCustomer.Statement();
        assertTrue(statement.contains("You earned 0"));
    }

    @Test
    public void shouldState2AmountWhenCallStatementWithRegularRentalFor1Day() {
        Rental rental = new RentalBuilder().Build();
        mCustomer.addRental(rental);
        String statement = mCustomer.Statement();
        assertTrue(statement.contains("Amount owed is 2"));
    }

    @Test
    public void shouldState5AmountWhenCallStatementWithRegularRentalFor4Days() {
        Rental rental = new RentalBuilder().SetRentalDays(4).Build();
        mCustomer.addRental(rental);
        String statement = mCustomer.Statement();
        assertTrue(statement.contains("Amount owed is 5"));
    }

    @Test
    public void shouldState1point5AmountWhenCallStatementWithChildrenRentalFor1Day() {
        Rental rental = new RentalBuilder().SetPriceCode(PriceCodes.Childrens).Build();
        mCustomer.addRental(rental);
        String statement = mCustomer.Statement();
        assertTrue(statement.contains("Amount owed is 1.5"));
    }

    @Test
    public void shouldState1point5AmountWhenCallStatementWithChildrenRentalFor4Day() {
        Rental rental = new RentalBuilder()
                .SetPriceCode(PriceCodes.Childrens)
                .SetRentalDays(4)
                .Build();
        mCustomer.addRental(rental);
        String statement = mCustomer.Statement();
        assertTrue(statement.contains("Amount owed is 1.5"));
    }

    @Test
    public void shouldState3AmountWhenCallStatementWithNewRentalFor1Days() {
        Rental rental = new RentalBuilder().SetPriceCode(PriceCodes.NewRelease).Build();
        mCustomer.addRental(rental);
        String statement = mCustomer.Statement();
        assertTrue(statement.contains("Amount owed is 3"));
    }

    @Test
    public void shouldState1FRPWhenCallStatementWithoutNewRelease() {
        Rental rental = new RentalBuilder().Build();
        mCustomer.addRental(rental);
        String statement = mCustomer.Statement();
        assertTrue(statement.contains("You earned 1"));
    }

    @Test
    public void shouldState2FRPWhenCallStatementWithNewReleaseAndDays2() {
        Rental rental = new RentalBuilder()
                .SetPriceCode(PriceCodes.NewRelease)
                .SetRentalDays(2)
                .Build();
        mCustomer.addRental(rental);
        String statement = mCustomer.Statement();
        assertTrue(statement.contains("You earned 2"));
    }
}
