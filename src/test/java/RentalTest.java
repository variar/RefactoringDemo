import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import com.scrumtrek.simplestore.Rental;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by omni on 19.11.15.
 */
public class RentalTest {

    @Test
    public void shouldReturnMovieWhenGetMovie()
    {
        Movie m = new Movie("123", PriceCodes.Childrens);
        Rental sut = new Rental(m, 10);
        Assert.assertEquals(m, sut.getMovie());
    }

    @Test
    public void shouldReturnDaysWhenGetRentalDays()
    {
        Movie m = new Movie("123", PriceCodes.Childrens);
        Rental sut = new Rental(m, 10);
        Assert.assertEquals(10, sut.getDaysRented());
    }
}
