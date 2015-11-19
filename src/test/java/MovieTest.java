import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {
    private static String _movieTitle = "TestMovie";
    private static PriceCodes _priceCode = PriceCodes.Regular;

    @Test
    public void shouldReturnTitleWhenGetTitle()
    {
        Movie sut = new Movie(_movieTitle, _priceCode);
        assertEquals(_movieTitle, sut.getTitle());
    }

    @Test
    public void shouldReturnPriceCodeWhenGetPriceCode()
    {
        Movie sut = new Movie(_movieTitle, _priceCode);
        assertEquals(_priceCode, sut.getPriceCode());
    }

    @Test
    public void shouldReturnNewPriceCodeWhenSetPriceCode()
    {
        Movie sut = new Movie(_movieTitle, _priceCode);
        sut.setPriceCode(PriceCodes.NewRelease);
        assertEquals(PriceCodes.NewRelease, sut.getPriceCode());
    }
}
