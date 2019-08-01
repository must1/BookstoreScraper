package bookstore.scraper.fetcher.empik;

import bookstore.scraper.book.Book;
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

import static bookstore.scraper.fetcher.empik.EmpikBookProvider.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmpikFetchingBookServiceTest {

    @Autowired
    EmpikFetchingBookService empikFetchingBookService;

    @Test
    public void get15BooksFromCrimeCategory() throws IOException {
        File in = getFile("/empik/CrimeCategoryEmpik.html");
        Document doc = Jsoup.parse(in, "UTF-8");

        List<Book> actualBooks = empikFetchingBookService.get15BooksFromCategory(doc);
        List<Book> expectedBooks = prepare15CrimeBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void get5Bestsellers() throws IOException {
        File in = getFile("/empik/BestsellersEmpik.html");
        Document doc = Jsoup.parse(in, "UTF-8");

        List<Book> actualBooks = empikFetchingBookService.get5BestSellersEmpik(doc);
        List<Book> expectedBooks = prepare5Bestsellers();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void getMostPreciseBook() throws IOException {
        File in = getFile("/empik/MostPreciseBookEmpik.html");
        Document doc = Jsoup.parse(in, "UTF-8");

        Book actualBooks = empikFetchingBookService.getMostPreciseEmpikBook(doc);
        Book expectedBooks = prepareMostPreciseBook();

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