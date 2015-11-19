import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Rental;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

    private Customer m_Customer;

    @Before
    public void createCustomer() {
        m_Customer = new Customer("name");
    }

    @Test
    public void shouldReturnNameWhenGetName() {
        Assert.assertEquals("name", m_Customer.getName());
    }

    @Test
    public void shouldNotCrashWhenCallStatement() {
        m_Customer.Statement();
    }

    @Test
    public void shouldNotCrashWhenAddRental() {
        Rental rental = org.mockito.Mockito.mock(Rental.class);
        m_Customer.addRental(rental);
    }

    @Test
    public void shouldState0AmountWhenCallStatementWithoutRentals() {
        String statement = m_Customer.Statement();
        Assert.assertTrue(statement.contains("Amount owed is 0"));
    }

    @Test
    public void shouldState0FRPWhenCallStatementWithoutRentals() {
        String statement = m_Customer.Statement();
        Assert.assertTrue(statement.contains("You earned 0"));
    }
}
