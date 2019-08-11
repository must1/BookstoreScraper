package bookstore.scraper.urlproperties;

import bookstore.scraper.enums.CategoryType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MerlinUrlPropertiesTest {

    @Autowired
    MerlinUrlProperties merlinUrlProperties;

    @Test
    public void getCrimeCategory() {
        String actual = merlinUrlProperties.getCategory(CategoryType.BESTSELLER);
        String expected = "https://merlin.pl/bestseller/?option_80=10349074";

        assertEquals(expected, actual);
    }

    @Test
    public void getBiographyCategory() {
        String actual = merlinUrlProperties.getCategory(CategoryType.BIOGRAPHY);
        String expected = "https://merlin.pl/catalog/ksiazki-m10349074/biografie-c100115/";

        assertEquals(expected, actual);
    }

    @Test
    public void getRomancesCategory() {
        String actual = merlinUrlProperties.getCategory(CategoryType.ROMANCES);
        String expected = "https://merlin.pl/catalog/ksiazki-m10349074/romanse-c1774/";

        assertEquals(expected, actual);
    }

    @Test
    public void getFantasyCategory() {
        String actual = merlinUrlProperties.getCategory(CategoryType.FANTASY);
        String expected = "https://merlin.pl/catalog/ksiazki-m10349074/fantastyka-c467/";

        assertEquals(expected, actual);
    }
}
