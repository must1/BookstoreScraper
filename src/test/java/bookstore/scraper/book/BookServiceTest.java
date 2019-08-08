package bookstore.scraper.book;

import bookstore.scraper.book.booksource.BookServiceSource;
import bookstore.scraper.book.booksource.empik.EmpikSource;
import bookstore.scraper.book.booksource.merlin.MerlinSource;
import bookstore.scraper.dataprovider.EmpikBookProvider;
import bookstore.scraper.dataprovider.MerlinBookProvider;
import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.enums.CategoryType;
import bookstore.scraper.urlproperties.EmpikUrlProperties;
import bookstore.scraper.urlproperties.MerlinUrlProperties;
import bookstore.scraper.utilities.JSoupConnector;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static bookstore.scraper.dataprovider.MergedBestsellersMapProvider.prepareExpectedMergedBestSellerMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    MerlinSource merlinSource;
    @Mock
    EmpikSource empikSource;
    @Mock
    BookServiceSource bookServiceSource;
    @Mock
    private EmpikUrlProperties empikMock;
    @Mock
    private MerlinUrlProperties merlinMock;
    @Mock
    JSoupConnector jSoupConnector;
    @Mock
    List<BookServiceSource> source;

    @InjectMocks
    BookService bookService;

    @Test
    public void getBooksByCategory() {
        List<Book> merlinBestsellers = MerlinBookProvider.prepare5Bestsellers();
        List<Book> empikBestsellers = EmpikBookProvider.prepare5Bestsellers();
        Document empikDocument = mock(Document.class);
        Document merlinDocument = mock(Document.class);

        source.add(empikSource);
        source.add(merlinSource);

        when(bookServiceSource.getName()).thenReturn(Bookstore.EMPIK);
        when(jSoupConnector.connect("https://www.empik.com/bestsellery/ksiazki")).thenReturn(empikDocument);
        when(empikMock.getBestSellers()).thenReturn("https://www.empik.com/bestsellery/ksiazki");
        when(empikSource.getBooksByCategory(CategoryType.CRIME)).thenReturn(empikBestsellers);

        when(bookServiceSource.getName()).thenReturn(Bookstore.MERLIN);
        when(jSoupConnector.connect("https://merlin.pl/bestseller/?option_80=10349074")).thenReturn(merlinDocument);
        when(merlinMock.getBestSellers()).thenReturn("https://merlin.pl/bestseller/?option_80=10349074");
        when(merlinSource.getBooksByCategory(CategoryType.CRIME)).thenReturn(merlinBestsellers);

        Map<Bookstore, List<Book>> actualMap = bookService.getBooksByCategory(CategoryType.CRIME);
        Map<Bookstore, List<Book>> expectedMap = prepareExpectedMergedBestSellerMap();

        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void getMostPreciseBOok() {
    }

    @Test
    public void getBestsellers() {
    }
}