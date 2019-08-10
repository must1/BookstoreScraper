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
    public void getBestsellerCategory() {
        String actual = merlinUrlProperties.getCategory(CategoryType.BESTSELLER);
        String expected = "https://merlin.pl/bestseller/?option_80=10349074";

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNotExistingCategory() {
        String actual = merlinUrlProperties.getCategory(CategoryType.DUMMY);
        String expected = "Unexpected categoryType:" + CategoryType.DUMMY;

        assertEquals(expected, actual);
    }
}
