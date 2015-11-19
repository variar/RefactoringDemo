import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import com.scrumtrek.simplestore.Rental;
import org.junit.Test;

import static org.junit.Assert.*;

public class RentalTest {

    @Test
    public void shouldReturnMovieWhenGetMovie()
    {
        Movie m = new Movie("123", PriceCodes.Childrens);
        Rental sut = new Rental(m, 10);
        assertEquals(m, sut.getMovie());
    }

    @Test
    public void shouldReturnDaysWhenGetRentalDays()
    {
        Movie m = new Movie("123", PriceCodes.Childrens);
        Rental sut = new Rental(m, 10);
        assertEquals(10, sut.getDaysRented());
    }
}
