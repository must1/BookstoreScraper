package bookstore.scraper.fetcher.merlin;

import bookstore.scraper.book.Book;
import bookstore.scraper.fetcher.empik.EmpikFetchingBookServiceTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static bookstore.scraper.fetcher.merlin.MerlinBookProvider.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MerlinFetchingBookServiceTest {

    @Autowired
    MerlinFetchingBookService merlinFetchingBookService;

    @Test
    public void getMostPreciseMerlinBook() throws IOException {
        File in = getFile("/merlin/MostPreciseBookMerlin.html");
        Document doc = Jsoup.parse(in, "UTF-8");

        Book actualBooks = merlinFetchingBookService.getMostPreciseMerlinBook(doc);
        Book expectedBooks = prepareMostPreciseBook();

        assertEquals(expectedBooks, actualBooks);

    }

    @Test
    public void get5BestSellersMerlin() {
    }

    @Test
    public void get15BooksFromCategory() throws IOException {
        File in = getFile("/merlin/CrimeCategoryMerlin.html");
        Document doc = Jsoup.parse(in, "UTF-8");

        List<Book> actualBooks = merlinFetchingBookService.get15BooksFromCategory(doc);
        List<Book> expectedBooks = prepare15CrimeBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    private File getFile(String resourceName) {
        try {
            return new File(EmpikFetchingBookServiceTest.class.getResource(resourceName).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }
}