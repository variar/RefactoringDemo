import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import com.scrumtrek.simplestore.Rental;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer m_Customer;

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
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.Regular), 1));
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("Amount owed is 2"));
    }

    @Test
    public void shouldState5AmountWhenCallStatementWithRegularRentalFor4Days() {
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.Regular), 4));
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("Amount owed is 5"));
    }

    @Test
    public void shouldState1point5AmountWhenCallStatementWithChildrenRentalFor1Day() {
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.Childrens), 1));
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("Amount owed is 1.5"));
    }

    @Test
    public void shouldState1point5AmountWhenCallStatementWithChildrenRentalFor4Day() {
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.Childrens), 4));
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("Amount owed is 1.5"));
    }

    @Test
    public void shouldState3AmountWhenCallStatementWithNewRentalFor1Days() {
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.NewRelease), 1));
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("Amount owed is 3"));
    }

    @Test
    public void shouldState1FRPWhenCallStatementWithoutNewRelease() {
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.Regular), 2));
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("You earned 1"));
    }

    @Test
    public void shouldState2FRPWhenCallStatementWithNewReleaseAndDays2() {
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.NewRelease), 2));
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("You earned 2"));
    }
}
