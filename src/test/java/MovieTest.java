import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by omni on 19.11.15.
 */
public class MovieTest {
    private static String _movieTitle = "TestMovie";
    private static PriceCodes _priceCode = PriceCodes.Regular;

    @Test
    public void shouldReturnTitleWhenGetTitle()
    {
        Movie sut = new Movie(_movieTitle, _priceCode);
        Assert.assertEquals(_movieTitle, sut.getTitle());
    }

    @Test
    public void shouldReturnPriceCodeWhenGetPriceCode()
    {
        Movie sut = new Movie(_movieTitle, _priceCode);
        Assert.assertEquals(_priceCode, sut.getPriceCode());
    }

    @Test
    public void shouldReturnNewPriceCodeWhenSetPriceCode()
    {
        Movie sut = new Movie(_movieTitle, _priceCode);
        sut.setPriceCode(PriceCodes.NewRelease);
        Assert.assertEquals(PriceCodes.NewRelease, sut.getPriceCode());
    }
}
