package bookstore.scraper.book.booksource.empik;

import bookstore.scraper.book.Book;
import bookstore.scraper.enums.CategoryType;
import bookstore.scraper.urlproperties.EmpikUrlProperties;
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

import static bookstore.scraper.dataprovider.EmpikBookProvider.prepare15CrimeBooks;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmpikSourceTest {

    @Mock
    JSoupConnector jSoupConnector;
    @Mock
    EmpikUrlProperties empikUrlProperties;

    @InjectMocks
    EmpikSource empikSource;

    /*@Before
    public void setUp() {
        when(empikUrlProperties.getConcreteBook()).thenReturn(anyString());
    }
*/
    @Test
    public void getBooksByCategory() throws IOException {
        File in = getFile("/empik/CrimeCategoryEmpik.html");
        Document empikDocument = Jsoup.parse(in, "UTF-8");

        when(jSoupConnector.connect(any())).thenReturn(empikDocument);
        when(empikUrlProperties.getConcreteBook()).thenReturn(anyString());

        List<Book> actualBooks = empikSource.getBooksByCategory(CategoryType.CRIME);
        List<Book> expectedBooks = prepare15CrimeBooks();

        assertEquals(expectedBooks, actualBooks);
    }


    /*@Test
    public void getMostPreciseBook() throws IOException {
        File in = getFile("/empik/MostPreciseBookEmpik.html");
        Document empikDocument = Jsoup.parse(in, "UTF-8");

        when(jSoupConnector.connect(any())).thenReturn(empikDocument);
        when(empikUrlProperties.getConcreteBook()).thenReturn(anyString());

        Book actualBooks = empikSource.getMostPreciseBook("W pustyni i w puszczy. Lektura z opracowaniem - Henryk Sienkiewicz");
        Book expectedBooks = prepareMostPreciseBook();

        assertEquals(expectedBooks, actualBooks);
    }*/

    @Test
    public void getBestSellers() {
    }

    private File getFile(String resourceName) {
        try {
            return new File(EmpikSourceTest.class.getResource(resourceName).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

}
