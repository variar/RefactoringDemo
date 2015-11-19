import com.scrumtrek.simplestore.Customer;
import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void shouldReturnNameWhenGetName() {
        Customer sut = new Customer("name");
        Assert.assertEquals("name", sut.getName());
    }

    @Test
    public void shouldNotCrashWhenCallStatement() {
        Customer sut = new Customer("name");
        sut.Statement();
    }
}
