import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import com.scrumtrek.simplestore.Rental;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer m_Customer;

    private class RentalBuilder {
        private String m_movieName = "test movie";
        private PriceCodes m_priceCode = PriceCodes.Regular;
        private int m_days = 1;

        public RentalBuilder SetMovieName(String name) {
            m_movieName = name;
            return this;
        }

        public RentalBuilder SetPriceCode(PriceCodes code) {
            m_priceCode = code;
            return this;
        }

        public RentalBuilder SetRentalDays(int days) {
            m_days = days;
            return this;
        }

        public Rental Build() {
            return new Rental(new Movie(m_movieName, m_priceCode), m_days);
        }
    }

    @Before
    public void createCustomer() {
        m_Customer = new Customer("name");
    }

    @Test
    public void shouldReturnNameWhenGetName() {
        assertEquals("name", m_Customer.getName());
    }

    @Test
    public void shouldNotCrashWhenCallStatement() {
        try {
            m_Customer.Statement();
        }
        catch (Exception err) {
            fail(err.getMessage());
        }
    }

    @Test
    public void shouldNotCrashWhenAddRental() {
        Rental rental = org.mockito.Mockito.mock(Rental.class);
        try {
            m_Customer.addRental(rental);
        }
        catch (Exception err) {
            fail(err.getMessage());
        }
    }

    @Test
    public void shouldState0AmountWhenCallStatementWithoutRentals() {
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("Amount owed is 0"));
    }

    @Test
    public void shouldState0FRPWhenCallStatementWithoutRentals() {
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("You earned 0"));
    }

    @Test
    public void shouldState2AmountWhenCallStatementWithRegularRentalFor1Day() {
        Rental rental = new RentalBuilder().Build();
        m_Customer.addRental(rental);
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("Amount owed is 2"));
    }

    @Test
    public void shouldState5AmountWhenCallStatementWithRegularRentalFor4Days() {
        Rental rental = new RentalBuilder().SetRentalDays(4).Build();
        m_Customer.addRental(rental);
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("Amount owed is 5"));
    }

    @Test
    public void shouldState1point5AmountWhenCallStatementWithChildrenRentalFor1Day() {
        Rental rental = new RentalBuilder().SetPriceCode(PriceCodes.Childrens).Build();
        m_Customer.addRental(rental);
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("Amount owed is 1.5"));
    }

    @Test
    public void shouldState1point5AmountWhenCallStatementWithChildrenRentalFor4Day() {
        Rental rental = new RentalBuilder()
                .SetPriceCode(PriceCodes.Childrens)
                .SetRentalDays(4)
                .Build();
        m_Customer.addRental(rental);
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("Amount owed is 1.5"));
    }

    @Test
    public void shouldState3AmountWhenCallStatementWithNewRentalFor1Days() {
        Rental rental = new RentalBuilder().SetPriceCode(PriceCodes.NewRelease).Build();
        m_Customer.addRental(rental);
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("Amount owed is 3"));
    }

    @Test
    public void shouldState1FRPWhenCallStatementWithoutNewRelease() {
        Rental rental = new RentalBuilder().Build();
        m_Customer.addRental(rental);
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("You earned 1"));
    }

    @Test
    public void shouldState2FRPWhenCallStatementWithNewReleaseAndDays2() {
        Rental rental = new RentalBuilder()
                .SetPriceCode(PriceCodes.NewRelease)
                .SetRentalDays(2)
                .Build();
        m_Customer.addRental(rental);
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("You earned 2"));
    }
}
