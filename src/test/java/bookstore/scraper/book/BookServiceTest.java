package bookstore.scraper.book;

import bookstore.scraper.account.LoggedAccountService;
import bookstore.scraper.book.booksource.BookServiceSource;
import bookstore.scraper.dataprovider.EmpikBookProvider;
import bookstore.scraper.dataprovider.MerlinBookProvider;
import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.enums.CategoryType;
import bookstore.scraper.historysystem.HistorySystemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static bookstore.scraper.dataprovider.MergedMapProvider.prepareCrimeBooksMap;
import static bookstore.scraper.dataprovider.MergedMapProvider.prepareExpectedMergedBestSellerMap;
import static bookstore.scraper.dataprovider.MergedMapProvider.prepareExpectedMergedMostPreciseBookMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    HistorySystemService historySystemService;
    @Mock
    LoggedAccountService loggedAccountService;

    @Before
    public void setUp() {
        when(loggedAccountService.getLoggedAccountID()).thenReturn(1);
    }

    @Test
    public void getBooksByCategory() {
        List<Book> merlinBestsellers = MerlinBookProvider.prepare15CrimeBooks();
        List<Book> empikBestsellers = EmpikBookProvider.prepare15CrimeBooks();

        BookServiceSource empikSource = mock(BookServiceSource.class);
        when(empikSource.getName()).thenReturn(Bookstore.EMPIK);
        when(empikSource.getBooksByCategory(CategoryType.CRIME)).thenReturn(empikBestsellers);

        BookServiceSource merlinSource = mock(BookServiceSource.class);
        when(merlinSource.getName()).thenReturn(Bookstore.MERLIN);
        when(merlinSource.getBooksByCategory(CategoryType.CRIME)).thenReturn(merlinBestsellers);

        List<BookServiceSource> sources = new ArrayList<>();
        sources.add(empikSource);
        sources.add(merlinSource);
        BookService service = new BookService(sources, historySystemService, loggedAccountService);

        Map<Bookstore, List<Book>> actualMap = service.getBooksByCategory(CategoryType.CRIME);
        Map<Bookstore, List<Book>> expectedMap = prepareCrimeBooksMap();

        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void getMostPreciseBOok() {
        Book merlinBook = MerlinBookProvider.prepareMostPreciseBook();
        Book empikBook = EmpikBookProvider.prepareMostPreciseBook();

        BookServiceSource empikSource = mock(BookServiceSource.class);
        when(empikSource.getName()).thenReturn(Bookstore.EMPIK);
        when(empikSource.getMostPreciseBook("")).thenReturn(empikBook);

        BookServiceSource merlinSource = mock(BookServiceSource.class);
        when(merlinSource.getName()).thenReturn(Bookstore.MERLIN);
        when(merlinSource.getMostPreciseBook("")).thenReturn(merlinBook);

        List<BookServiceSource> sources = new ArrayList<>();
        sources.add(empikSource);
        sources.add(merlinSource);
        BookService service = new BookService(sources, historySystemService, loggedAccountService);

        Map<Bookstore, Book> expectedMap = prepareExpectedMergedMostPreciseBookMap();
        Map<Bookstore, Book> actualMap = service.getMostPreciseBook("");

        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void getBestsellers() {
        List<Book> merlinBestsellers = MerlinBookProvider.prepare5Bestsellers();
        List<Book> empikBestsellers = EmpikBookProvider.prepare5Bestsellers();

        BookServiceSource empikSource = mock(BookServiceSource.class);
        when(empikSource.getName()).thenReturn(Bookstore.EMPIK);
        when(empikSource.getBestSellers()).thenReturn(empikBestsellers);

        BookServiceSource merlinSource = mock(BookServiceSource.class);
        when(merlinSource.getName()).thenReturn(Bookstore.MERLIN);
        when(merlinSource.getBestSellers()).thenReturn(merlinBestsellers);

        List<BookServiceSource> sources = new ArrayList<>();
        sources.add(empikSource);
        sources.add(merlinSource);
        BookService service = new BookService(sources, historySystemService, loggedAccountService);

        Map<Bookstore, List<Book>> actualMap = service.getBestsellers();
        Map<Bookstore, List<Book>> expectedMap = prepareExpectedMergedBestSellerMap();

        assertEquals(expectedMap, actualMap);
    }
}