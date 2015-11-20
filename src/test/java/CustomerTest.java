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
    public void shouldStateNotNullAmountWhenCallStatementWithRentals() {
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.Regular), 3));
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.Childrens), 4));
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.NewRelease), 3));
        String statement = m_Customer.Statement();
        assertFalse(statement.contains("You earned 0"));
    }

    @Test
    public void shouldStateNotNullAmountWhenCallStatementWithRentals1() {
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.Regular), 1));
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.Childrens), 4));
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.NewRelease), 1));
        String statement = m_Customer.Statement();
        assertFalse(statement.contains("You earned 0"));
    }

    @Test
    public void shouldStateNotNullAmountWhenCallStatementWithRentals2() {
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.Regular), 3));
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.Childrens), 2));
        String statement = m_Customer.Statement();
        assertFalse(statement.contains("You earned 0"));
    }

    @Test
    public void shouldStateNotNullAmountWhenCallStatementWithRentals3() {
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.Regular), 1));
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.Childrens), 1));
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.NewRelease), 1));
        String statement = m_Customer.Statement();
        assertFalse(statement.contains("You earned 0"));
    }

    @Test
    public void shouldState2FRPWhenCallStatementWithOnlyNewReleaseAndDays2() {
        m_Customer.addRental(new Rental(new Movie("123", PriceCodes.NewRelease), 2));
        String statement = m_Customer.Statement();
        assertTrue(statement.contains("You earned 2"));
    }
}
