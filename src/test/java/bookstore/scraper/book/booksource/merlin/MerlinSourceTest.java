package bookstore.scraper.book.booksource.merlin;

import bookstore.scraper.book.Book;
import bookstore.scraper.enums.CategoryType;
import bookstore.scraper.urlproperties.MerlinUrlProperties;
import bookstore.scraper.utilities.JSoupConnector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static bookstore.scraper.dataprovider.MerlinBookProvider.prepare15CrimeBooks;
import static bookstore.scraper.dataprovider.MerlinBookProvider.prepare5Bestsellers;
import static bookstore.scraper.dataprovider.MerlinBookProvider.prepareMostPreciseBook;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MerlinSourceTest {

    @Mock
    JSoupConnector jSoupConnector;
    @Mock
    MerlinUrlProperties merlinUrlProperties;

    @InjectMocks
    MerlinSource merlinSource;

    @Test
    public void getBooksByCategory() throws IOException {
        File in = getFile("/merlin/CrimeCategoryMerlin.html");
        Document empikDocument = Jsoup.parse(in, "UTF-8");

        when(jSoupConnector.connect(any())).thenReturn(empikDocument);
        when(merlinUrlProperties.getConcreteBook()).thenReturn(anyString());

        List<Book> actualBooks = merlinSource.getBooksByCategory(CategoryType.CRIME);
        List<Book> expectedBooks = prepare15CrimeBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void getMostPreciseBook() throws IOException {
        File in = getFile("/merlin/MostPreciseBookMerlin.html");
        Document empikDocument = Jsoup.parse(in, "UTF-8");

        when(jSoupConnector.connect(any())).thenReturn(empikDocument);
        when(merlinUrlProperties.getCategory(CategoryType.MOST_PRECISE_BOOK)).thenReturn("https://merlin.pl/catalog/ksiazki-m10349074/?q=%s");
        when(merlinUrlProperties.getConcreteBook()).thenReturn(anyString());

        Book actualBooks = merlinSource.getMostPreciseBook("W pustyni i w puszczy. Lektura z opracowaniem - Henryk Sienkiewicz");
        Book expectedBooks = prepareMostPreciseBook();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void getBestSellers() throws IOException {
        File in = getFile("/merlin/BestsellersMerlin.html");
        Document empikDocument = Jsoup.parse(in, "UTF-8");

        when(jSoupConnector.connect(any())).thenReturn(empikDocument);
        when(merlinUrlProperties.getConcreteBook()).thenReturn(anyString());

        List<Book> actualBooks = merlinSource.getBestSellers();
        List<Book> expectedBooks = prepare5Bestsellers();

        assertEquals(expectedBooks, actualBooks);
    }

    private File getFile(String resourceName) {
        try {
            return new File(MerlinSourceTest.class.getResource(resourceName).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }
}